/*
 * Copyright 2019 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#include "ktnumpy_includes.h"

static PyObject *module_tracebake = NULL, *extract_tb = NULL;

int python_exception (JNIEnv *env)
{
  PyObject *ptype = NULL;
  PyObject *pvalue = NULL;
  PyObject *ptraceback = NULL;
  PyObject *pstack = NULL;
  PyObject *message = NULL;

  jstring jmessage = NULL;
  jobject jexception = NULL;

  const char *cmes = NULL;

  if (!PyErr_Occurred ())
    {
      return 0;
    }

  if (PyErr_ExceptionMatches (PyExc_SystemExit))
    {
      PyErr_PrintEx (1);
      return 0;
    }


  // trace
  PyErr_Fetch (&ptype, &pvalue, &ptraceback);

  if (ptype == NULL && pvalue == NULL && ptraceback == NULL)
    {
      return 0;
    }

  if (ptype != NULL)
    {
      message = PyObject_Str (ptype);

      if (pvalue != NULL)
        {
          PyObject *val = NULL;
          if (PyObject_TypeCheck (pvalue, (PyTypeObject *) PyExc_BaseException))
            {
              PyObject *args = PyObject_GetAttrString (pvalue, "args");
              if (args != NULL && PyTuple_Check(args) && PyTuple_Size (args) > 0)
                {
                  PyObject *mes = PyTuple_GetItem (args, 0);
                  Py_INCREF(mes);
                  Py_DECREF(pvalue);
                  Py_DECREF(args);
                  pvalue = mes;
                }
            }

          val = PyObject_Str (pvalue);
          if (val != NULL)
            {
              PyObject *tmp;
              tmp = PyUnicode_FromFormat ("%U: %U", message, val);
              Py_DECREF (val);
              Py_XDECREF (message);
              message = tmp;
            }

          // create NumKtException
          cmes = PyString_AsString (message);
          jmessage = (*env)->NewStringUTF (env, cmes);
          jexception = numkt_NumKtException_init_string (env, jmessage);
          (*env)->DeleteLocalRef (env, jmessage);
          if ((*env)->ExceptionCheck (env) || !jexception)
            {
              PyErr_Format (PyExc_RuntimeError, "throw Exception failed.");
              return 1;
            }

          // traceback
          if (ptraceback != NULL)
            {
              if (module_tracebake == NULL)
                {
                  module_tracebake = PyImport_ImportModule ("traceback");
                  if (module_tracebake == NULL)
                    {
                      printf ("Error importing module traceback\n");
                    }
                }
              if (extract_tb == NULL)
                {
                  extract_tb = PyString_FromString ("extract_tb");
                  if (extract_tb == NULL)
                    {
                      printf ("Error create python str 'extract_tb'\n");
                    }
                }

              pstack = PyObject_CallMethodObjArgs (module_tracebake, extract_tb, ptraceback, NULL);
              if (PyErr_Occurred ())
                {
                  PyErr_Print ();
                }

              if (pstack != NULL)
                {
                  jsize stack_size;
                  jobjectArray stack_array = NULL, java_stack = NULL, reverse_java_stack;
                  size_t count, java_stack_length, index;

                  //stackTraceElem
                  //constructor

                  stack_size = PyList_Size (pstack);
                  stack_array = (*env)->NewObjectArray (env, stack_size, STACK_TRACE_ELEMENT_TYPE, NULL);
                  if ((*env)->ExceptionCheck (env) || stack_array == NULL)
                    {
                      PyErr_Format (PyExc_RuntimeError, "Error to create StackTraceElement[].\n");
                      Py_DECREF (pstack);
                      return 1;
                    }

                  count = 0;
                  for (int i = 0; i < stack_size; ++i)
                    {
                      PyObject *st_entry, *pline;
                      const char *py_file_name = NULL;
                      const char *py_func_name = NULL;
                      int py_line_num;

                      st_entry = PyList_GetItem (pstack, i);

                      py_file_name = PyString_AsString (PySequence_GetItem (st_entry, 0));
                      py_line_num = PyInt_AsLong (PySequence_GetItem (st_entry, 1));
                      py_func_name = PyString_AsString(PySequence_GetItem (st_entry, 2));
                      pline = PySequence_GetItem (st_entry, 3);

                      if (pline != Py_None)
                        {
                          char *py_file_no_ext, *last_dot, *py_file_no_dir, *last_backslash;
                          int name_length;
                          jobject element;
                          jstring file_no_ext, file_no_dir, func_str;

                          name_length = (int) strlen (py_file_name);
                          py_file_no_ext = malloc (sizeof (char) * (name_length + 1));
                          strcpy (py_file_no_ext, py_file_name);
                          last_dot = strrchr (py_file_no_ext, '.');
                          if (last_dot != NULL)
                            {
                              *last_dot = '\0';
                            }

                          // dir path
                          py_file_no_dir = malloc (sizeof (char) * (name_length + 1));
                          last_backslash = strrchr (py_file_name, FILE_SEP);
                          if (last_backslash != NULL)
                            {
                              strcpy (py_file_no_dir, last_backslash + 1);
                            }
                          else
                            {
                              strcpy(py_file_no_dir, py_file_name);
                            }

                          file_no_dir = (*env)->NewStringUTF (env, py_file_no_dir);
                          file_no_ext = (*env)->NewStringUTF (env, py_file_no_ext);
                          func_str = (*env)->NewStringUTF (env, py_func_name);

                          element = java_lang_StackTraceElement_init (env, file_no_ext, func_str, file_no_dir, py_line_num);
                          if ((*env)->ExceptionCheck (env) || !element)
                            {
                              PyErr_Format (PyExc_RuntimeError, "Error to create StackTraceElement for python %s:%i.",
                                            py_file_name, py_line_num);
                              free (py_file_no_dir);
                              free (py_file_no_ext);
                              Py_DECREF(pstack);
                              return 1;
                            }

                          (*env)->SetObjectArrayElement (env, stack_array, i, element);

                          count++;
                          free (py_file_no_dir);
                          free (py_file_no_ext);
                          (*env)->DeleteLocalRef (env, file_no_dir);
                          (*env)->DeleteLocalRef (env, file_no_ext);
                          (*env)->DeleteLocalRef (env, func_str);
                          (*env)->DeleteLocalRef (env, element);
                        }
                    }
                  Py_DECREF (pstack);

                  java_stack = java_lang_Throwable_getStackTrace (env, jexception);
                  if ((*env)->ExceptionCheck (env) || !java_stack)
                    {
                      PyErr_Format (PyExc_RuntimeError, "Get stack trace fail.\n");
                      return 1;
                    }
                  java_stack_length = (*env)->GetArrayLength (env, java_stack);

                  reverse_java_stack = (*env)->NewObjectArray (env, (count
                                                                     + java_stack_length), STACK_TRACE_ELEMENT_TYPE, NULL);
                  if ((*env)->ExceptionCheck (env) || !reverse_java_stack)
                    {
                      PyErr_Format (PyExc_RuntimeError, "Error to create reverse StackTraceElement[].\n");
                      return 1;
                    }

                  index = 0;
                  for (int i = stack_size - 1; i > -1; --i)
                    {
                      jobject element = (*env)->GetObjectArrayElement (env, stack_array, i);
                      if (element != NULL)
                        {
                          (*env)->SetObjectArrayElement (env, reverse_java_stack, index, element);
                          (*env)->DeleteLocalRef (env, element);
                          index++;
                        }
                    }
                  for (int i = 0; i < java_stack_length; ++i)
                    {
                      jobject element = (*env)->GetObjectArrayElement (env, java_stack, i);
                      (*env)->SetObjectArrayElement (env, reverse_java_stack, index, element);
                      (*env)->DeleteLocalRef (env, element);
                      index++;
                    }
                  (*env)->DeleteLocalRef (env, stack_array);
                  (*env)->DeleteLocalRef (env, java_stack);

                  java_lang_Throwable_setStackTrace (env, jexception, reverse_java_stack);
                  if ((*env)->ExceptionCheck (env))
                    {
                      fprintf (stderr, "Error while processing NumKtException.\n");
                      PyErr_Restore (ptype, pvalue, ptraceback);
                      PyErr_Print ();
                      return 1;
                    }
                  (*env)->DeleteLocalRef (env, reverse_java_stack);
                }
            }
        }
    }

  Py_XDECREF (ptype);
  Py_XDECREF (pvalue);
  Py_XDECREF (ptraceback);

  if (jexception != NULL)
    {
      Py_XDECREF (message);
      (*env)->Throw (env, jexception);
    }
  else if (message)
    {
      cmes = PyString_AsString(message);
      Py_XDECREF(message);
      (*env)->ThrowNew (env, NUMKTEXCEPTION_TYPE, cmes);
    }

  return 1;
}