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

#ifdef __unix__
#include <dlfcn.h>
#endif

static PyThreadState *mainThreadState = NULL;

/*
 * Class:     org_jetbrains_numkt_Interpreter
 * Method:    initializePython
 * Signature: (Ljava/lang/String;Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_org_jetbrains_numkt_Interpreter_initializePython
    (JNIEnv *env, jobject jobj, jstring pythonHome, jstring ldpython_lib)
{

#ifdef _DLFCN_H
  // https://github.com/numpy/numpy/issues/13717
  const char *ldlib = (*env)->GetStringUTFChars (env, ldpython_lib, NULL);
  void *dlres = dlopen (ldlib, RTLD_LAZY | RTLD_GLOBAL);
  if (dlres)
    {
      dlclose (dlres);
    }
  else
    {
      dlerror ();
      fprintf (stderr, "Error linked ldpython_lib.\n");
    }
  (*env)->ReleaseStringUTFChars (env, ldpython_lib, ldlib);
#endif

  const char *home = (*env)->GetStringUTFChars (env, pythonHome, NULL);
  wchar_t *PYTHONHOME = Py_DecodeLocale (home, NULL);
  (*env)->ReleaseStringUTFChars (env, pythonHome, home);

  Py_SetPythonHome (PYTHONHOME);

  Py_SetProgramName (L"ktnumpy");
  Py_Initialize ();

  mainThreadState = PyThreadState_Get ();

  ktnumpy_init (env);
}

/*
 * Class:     org_jetbrains_numkt_Interpreter
 * Method:    callFunc_00024kotlin_numpy
 * Signature: ([Ljava/lang/String;[Ljava/lang/Object;Ljava/util/Map;)Lorg/jetbrains/numkt/core/KtNDArray;
 */
JNIEXPORT jobject JNICALL
Java_org_jetbrains_numkt_Interpreter_callFunc_00024kotlin_1numpy___3Ljava_lang_String_2_3Ljava_lang_Object_2Ljava_util_Map_2
    (JNIEnv *env, jobject jobj, jobjectArray arr_names_func, jobjectArray args, jobject kwargs)
{
  jobject res = NULL;

  res = invoke_call_function (env, arr_names_func, args, kwargs);

  return res;
}

/*
 * Class:     org_jetbrains_numkt_Interpreter
 * Method:    callFunc_00024kotlin_numpy
 * Signature: ([Ljava/lang/String;[Ljava/lang/Object;Ljava/util/Map;Ljava/lang/Class;)Ljava/lang/Object;
 */
JNIEXPORT jobject JNICALL
Java_org_jetbrains_numkt_Interpreter_callFunc_00024kotlin_1numpy___3Ljava_lang_String_2_3Ljava_lang_Object_2Ljava_util_Map_2Ljava_lang_Class_2
    (JNIEnv *env, jobject jobj, jobjectArray arr_names_func, jobjectArray args, jobject kwargs, jclass clazz)
{
  jobject res = NULL;

  res = invoke_call_function_with_class (env, arr_names_func, args, kwargs, clazz);

  return res;
}

/*
 * Class:     org_jetbrains_numkt_Interpreter
 * Method:    getField_00024kotlin_numpy
 * Signature: (Ljava/lang/String;JLjava/lang/Class;)Ljava/lang/Object;
 */
JNIEXPORT jobject JNICALL Java_org_jetbrains_numkt_Interpreter_getField_00024kotlin_1numpy
    (JNIEnv *env, jobject jobj, jstring name_field, jlong pointer, jclass clazz)
{
  jobject res = NULL;
  const char *name = NULL;

  name = jstring_to_char (env, name_field);

  if (strcmp (name, "shape") == 0)
    {
      res = get_shape (env, (PyArrayObject *) pointer);
    }
  else if (strcmp (name, "ndim") == 0)
    {
      res = get_ndim (env, (PyArrayObject *) pointer);
    }
  else if (strcmp (name, "itemsize") == 0)
    {
      res = get_itemsize (env, (PyArrayObject *) pointer);
    }
  else if (strcmp (name, "size") == 0)
    {
      res = get_size (env, (PyArrayObject *) pointer);
    }
  else if (strcmp (name, "strides") == 0)
    {
      res = get_strides (env, (PyArrayObject *) pointer);
    }
  else if (strcmp (name, "dtype") == 0)
    {
      res = get_jdtype (env, (PyArrayObject *) pointer);
    }
  else if (strcmp (name, "hashCode") == 0)
    {
      jint i = PyObject_Hash ((PyObject *) pointer);
      res = java_lang_Integer_new (env, i);
    }
  else if (strcmp (name, "toString") == 0)
    {
      PyObject *str = PyObject_Str ((PyObject *) pointer);
      res = pyobject_to_jobject (env, str, clazz);
      Py_XDECREF (str);
    }

  release_utf_char (env, name_field, name);

  return res;
}

/*
 * Class:     org_jetbrains_numkt_Interpreter
 * Method:    getValue_00024kotlin_numpy
 * Signature: (J[J)Ljava/lang/Object;
 */
JNIEXPORT jobject JNICALL Java_org_jetbrains_numkt_Interpreter_getValue_00024kotlin_1numpy__J_3J
    (JNIEnv *env, jobject jobj, jlong pointer, jlongArray jlong_array)
{
  return get_value (env, (PyObject *) pointer, jlong_array);
}

/*
 * Class:     org_jetbrains_numkt_Interpreter
 * Method:    getValue_00024kotlin_numpy
 * Signature: (J[Lorg/jetbrains/numkt/core/Slice;)Lorg/jetbrains/numkt/core/KtNDArray;
 */
JNIEXPORT jobject JNICALL
Java_org_jetbrains_numkt_Interpreter_getValue_00024kotlin_1numpy__J_3Lorg_jetbrains_numkt_core_Slice_2
    (JNIEnv *env, jobject jobj, jlong pointer, jobjectArray jobject_array)
{
  return get_ndvalue (env, (PyObject *) pointer, jobject_array);
}

/*
 * Class:     org_jetbrains_numkt_Interpreter
 * Method:    setValue_00024kotlin_numpy
 * Signature: (J[JLjava/lang/Object;)V
 */
JNIEXPORT void JNICALL Java_org_jetbrains_numkt_Interpreter_setValue_00024kotlin_1numpy__J_3JLjava_lang_Object_2
    (JNIEnv *env, jobject jobj, jlong pointer, jlongArray jlong_array, jobject element)
{
  set_value (env, (PyObject *) pointer, jlong_array, element);
}

/*
 * Class:     org_jetbrains_numkt_Interpreter
 * Method:    setValue_00024kotlin_numpy
 * Signature: (J[Lorg/jetbrains/numkt/core/Slice;Ljava/lang/Object;)V
 */
JNIEXPORT void JNICALL
Java_org_jetbrains_numkt_Interpreter_setValue_00024kotlin_1numpy__J_3Lorg_jetbrains_numkt_core_Slice_2Ljava_lang_Object_2
    (JNIEnv *env, jobject jobj, jlong pointer, jobjectArray jlong_array, jobject element)
{
  set_ndvalue (env, (PyObject *) pointer, jlong_array, element);
}

/*
 * Class:     org_jetbrains_numkt_Interpreter
 * Method:    freeArray_00024kotlin_numpy
 * Signature: (JLjava/nio/Buffer;)I
 */
JNIEXPORT jint JNICALL Java_org_jetbrains_numkt_Interpreter_freeArray_00024kotlin_1numpy
    (JNIEnv *env, jobject jobj, jlong pointer, jobject buf)
{
  PyEval_AcquireThread (mainThreadState);
  Py_XDECREF ((PyArrayObject *) pointer);
  python_exception (env);
  PyEval_ReleaseThread (mainThreadState);

  return 0;
}



/*
 * Class:     org_jetbrains_numkt_Interpreter
 * Method:    closePython
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jetbrains_numkt_Interpreter_closePython
    (JNIEnv *env, jobject jobj)
{
  Py_Finalize ();
}