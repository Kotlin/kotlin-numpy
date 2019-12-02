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

#define JBYTE_MAX  127
#define JBYTE_MIN (-128)

#define JSHORT_MAX  32767
#define JSHORT_MIN (-32768)

#define JINT_MAX  2147483647
#define JINT_MIN (-2147483648)

#define JLONG_MAX  9223372036854775807
#define JLONG_MIN (-1 * JLONG_MAX -1)

jboolean PyObject_As_jboolean (PyObject *py_object)
{
  if (PyObject_IsTrue (py_object))
    {
      return JNI_TRUE;
    }
  else
    {
      return JNI_FALSE;
    }
}

jbyte PyObject_As_jbyte (PyObject *py_object)
{
  PyObject *py_index;
  long b;
  py_index = PyNumber_Index (py_object);
  if (py_index == NULL)
    {
      return -1;
    }
  b = PyLong_AsLong (py_index);
  Py_DECREF (py_index);
  if (b < JBYTE_MIN || b > JBYTE_MAX)
    {
      PyErr_Format (PyExc_OverflowError, "%ld is outside the valid range of a Java byte.", b);
      return -1;
    }
  return (jbyte) b;
}

jshort PyObject_As_jshort (PyObject *py_object)
{
  PyObject *py_index;
  long s;
  py_index = PyNumber_Index (py_object);
  if (py_index == NULL)
    {
      return -1;
    }
  s = PyLong_AsLong (py_index);
  Py_DECREF(py_index);
  if (s < JSHORT_MIN || s > JSHORT_MAX)
    {
      PyErr_Format (PyExc_OverflowError, "%ld is outside the valid range of a Java short.", s);
      return -1;
    }
  return (jshort) s;
}

jint PyObject_As_jint (PyObject *py_object)
{
  PyObject *py_index;
  long i;
  py_index = PyNumber_Index (py_object);
  if (py_index == NULL)
    {
      return -1;
    }
  i = PyLong_AsLong (py_index);
  Py_DECREF(py_index);
  if (i < JINT_MIN || i > JINT_MAX)
    {
      PyErr_Format (PyExc_OverflowError, "%ld is outside the valid range of a Java int.", i);
      return -1;
    }
  return (jint) i;
}

jlong PyObject_As_jlong (PyObject *py_object)
{
  PyObject *py_index;
  PY_LONG_LONG j;
  py_index = PyNumber_Index (py_object);
  if (py_index == NULL)
    {
      return -1;
    }
  j = PyLong_AsLongLong (py_index);
  Py_DECREF(py_index);
  if (j < JLONG_MIN || j > JLONG_MAX)
    {
      PyErr_Format (PyExc_OverflowError, "%lld is outside the valid range of a Java long.", j);
      return -1;
    }
  return (jlong) j;
}

jfloat PyObject_As_jfloat (PyObject *py_object)
{
  return (jfloat) PyFloat_AsDouble (py_object);
}

jdouble PyObject_As_jdouble (PyObject *py_object)
{
  return (jdouble) PyFloat_AsDouble (py_object);
}

jstring PyObject_As_jstring (JNIEnv *env, PyObject *py_object)
{
  PyObject *bytes = NULL;
  jstring result = NULL;
  if (PyUnicode_READY(py_object) != 0)
    {
      return NULL;
    }
  else if (PyUnicode_KIND((py_object)) == PyUnicode_2BYTE_KIND)
    {
      Py_UCS2 *data = PyUnicode_2BYTE_DATA(py_object);
      Py_ssize_t length = PyUnicode_GET_LENGTH(py_object);
      return (*env)->NewString (env, data, (jsize) length);
    }

  bytes = PyUnicode_AsUTF16String (py_object);
  if (bytes == NULL)
    {
      return NULL;
    }

  result = (*env)->NewString (env, (jchar *) (PyBytes_AS_STRING(bytes) + 2),
                              (jsize) (PyBytes_GET_SIZE(bytes) - 2) / 2);
  Py_DECREF(bytes);
  return result;
}

jchar PyObject_As_jchar (PyObject *py_object)
{
  if (PyUnicode_Check(py_object))
    {
      if (PyUnicode_READY(py_object) != 0)
        {
          return 0;
        }
      else if (PyUnicode_GET_LENGTH(py_object) == 1)
        {
          if (PyUnicode_KIND((py_object)) == PyUnicode_1BYTE_KIND)
            {
              return (jchar) PyUnicode_1BYTE_DATA(py_object)[0];
            }
          else if (PyUnicode_KIND((py_object)) == PyUnicode_2BYTE_KIND)
            {
              return (jchar) PyUnicode_2BYTE_DATA(py_object)[0];
            }
        }
    }
  PyErr_Format (PyExc_TypeError, "Expected char but received a %s.", py_object->ob_type->tp_name);
  return 0;
}

static jobject pyunicode_as_jobject (JNIEnv *env, PyObject *py_object, jclass clazz)
{
  if ((*env)->IsAssignableFrom (env, STRING_TYPE, clazz))
    {
      return PyObject_As_jstring (env, py_object);
    }
  else if ((*env)->IsAssignableFrom (env, CHAR_TYPE, clazz))
    {
      jobject result;
      jchar c = PyObject_As_jchar (py_object);
      if (c == 0 && PyErr_Occurred ())
        {
          return NULL;
        }
      result = java_lang_Character_new (env, c);
      if (!result)
        {
          printf ("Warning: to create new Character value!\n");
          return NULL;
        }
      return result;
    }
  return NULL;
}

static jobject pybool_as_jobject (JNIEnv *env, PyObject *py_object, jclass clazz)
{
  if ((*env)->IsAssignableFrom (env, BOOLEAN_TYPE, clazz))
    {
      jobject result = NULL;
      jboolean z = PyObject_As_jboolean (py_object);
      if (PyErr_Occurred ())
        {
          return NULL;
        }
      result = java_lang_Boolean_new (env, z);
      if (!result)
        {
          printf ("Warning: to create new Boolean value!\n");
          return NULL;
        }
      return result;
    }
  printf ("pybool_as_jobject: Raise type error!\n");
  return NULL;
}

static jobject pylong_as_jobject (JNIEnv *env, PyObject *py_object, jclass clazz)
{
  jobject result = NULL;
  if ((*env)->IsAssignableFrom (env, BYTE_TYPE, clazz))
    {
      jbyte b = PyObject_As_jbyte (py_object);
      if (b == -1 && PyErr_Occurred ())
        {
          return NULL;
        }
      result = java_lang_Byte_new (env, b);
      if (!result)
        {
          printf ("Warning: to create new Byte value!\n");
        }
    }
  else if ((*env)->IsAssignableFrom (env, SHORT_TYPE, clazz))
    {
      jshort s = PyObject_As_jshort (py_object);
      if (s == -1 && PyErr_Occurred ())
        {
          return NULL;
        }
      result = java_lang_Short_new (env, s);
      if (!result)
        {
          printf ("Warning: to create new Short value!\n");
        }
    }
  else if ((*env)->IsAssignableFrom (env, INT_TYPE, clazz))
    {
      jint i = PyObject_As_jint (py_object);
      if (i == -1 && PyErr_Occurred ())
        {
          return NULL;
        }
      result = java_lang_Integer_new (env, i);
      if (!result)
        {
          printf ("Warning: to create new Integer value!\n");
        }
    }
  else if ((*env)->IsAssignableFrom (env, LONG_TYPE, clazz))
    {
      jlong j = PyObject_As_jlong (py_object);
      if (j == -1 && PyErr_Occurred ())
        {
          return NULL;
        }
      result = java_lang_Long_new (env, j);
      if (!result)
        {
          printf ("Warning: to create new Long value!\n");
        }
    }
  return result;
}

static jobject pyfloat_as_jobject (JNIEnv *env, PyObject *py_object, jclass clazz)
{
  jobject result = NULL;
  if ((*env)->IsAssignableFrom (env, FLOAT_TYPE, clazz))
    {
      jfloat f = PyObject_As_jfloat (py_object);
      if (f == -1.0 && PyErr_Occurred ())
        {
          return NULL;
        }
      result = java_lang_Float_new (env, f);
      if (!result)
        {
          printf ("Warning: to create new Float value!\n");
        }
    }
  else if ((*env)->IsAssignableFrom (env, DOUBLE_TYPE, clazz))
    {
      jdouble d = PyObject_As_jdouble (py_object);
      if (d == -1.0 && PyErr_Occurred ())
        {
          return NULL;
        }
      result = java_lang_Double_new (env, d);
      if (!result)
        {
          printf ("Warning: to create new Double value!\n");
        }
    }
  return result;
}

#define pyfastsequence_as_primitive_array(jtype, Type)                  \
    jtype *buf = malloc(size*sizeof(jtype));                            \
    jtype##Array jarray = (*env)->New##Type##Array(env, (jsize) size);  \
    if (jarray == NULL) {                                               \
        free(buf);                                                      \
        return (*env)->PopLocalFrame(env, NULL);                        \
    }                                                                   \
    for (i = 0; i < size; i++) {                                        \
        PyObject *item = PySequence_Fast_GET_ITEM(py_object, i);        \
        buf[i] = PyObject_As_##jtype(item);                             \
        if (PyErr_Occurred()){                                          \
            free(buf);                                                  \
            return (*env)->PopLocalFrame(env, NULL);                    \
        }                                                               \
    }                                                                   \
    (*env)->Set##Type##ArrayRegion(env, jarray,0, (jsize) size, buf);   \
    free(buf);                                                          \
    return (*env)->PopLocalFrame(env, jarray);

static jobject pyseq_as_jobject (JNIEnv *env, PyObject *py_object, jclass clazz)
{
  jboolean isArray;
  // list
  if ((*env)->IsAssignableFrom (env, LIST_TYPE, clazz))
    {
      jobject jlist;
      Py_ssize_t size, i;

      size = PySequence_Fast_GET_SIZE(py_object);

      if ((*env)->PushLocalFrame (env, JLOCAL_REFS) != 0)
        {
          return NULL;
        }

      jlist = java_util_ArrayList_new (env, (jint) size);
      if (!jlist)
        {
          return (*env)->PopLocalFrame (env, NULL);
        }

      for (i = 0; i < size; i++)
        {
          jobject value;
          PyObject *item = PySequence_Fast_GET_ITEM(py_object, i);
          value = pyobject_to_jobject (env, item, OBJECT_TYPE);
          if (value == NULL && PyErr_Occurred ())
            {
              return (*env)->PopLocalFrame (env, NULL);
            }
          java_util_List_add (env, jlist, value);
          (*env)->DeleteLocalRef (env, value);
        }

      return (*env)->PopLocalFrame (env, jlist);
    }

  if ((*env)->IsSameObject (env, PAIR_TYPE, clazz))
    {
      jobject first = NULL;
      jobject second = NULL;

      PyObject *pyf = PySequence_GetItem (py_object, 0);
      PyObject *pys = PySequence_GetItem (py_object, 1);

      first = pyobject_to_jobject (env, pyf, OBJECT_TYPE);
      second = pyobject_to_jobject (env, pys, OBJECT_TYPE);
      if ((first == NULL || second == NULL) && PyErr_Occurred ())
        {
          return (*env)->PopLocalFrame (env, NULL);
        }
      return kotlin_Pair_new (env, first, second);
    }

  // array
  isArray = java_lang_Class_IsArray (env, clazz);
  if (isArray)
    {
      jclass componentType;
      Py_ssize_t size, i;
      size = PySequence_Fast_GET_SIZE(py_object);

      if ((*env)->PushLocalFrame (env, JLOCAL_REFS) != 0)
        {
          return NULL;
        }

      componentType = java_lang_Class_getComponentType (env, clazz);

      if ((*env)->IsAssignableFrom (env, componentType, OBJECT_TYPE))
        {
          jobjectArray jarray = (*env)->NewObjectArray (env, (jsize) size, componentType,
                                                        NULL);
          if (!jarray)
            {
              return (*env)->PopLocalFrame (env, NULL);
            }
          for (i = 0; i < size; i++)
            {
              jobject value;
              PyObject *item = PySequence_Fast_GET_ITEM(py_object, i);
              value = pyobject_to_jobject (env, item, componentType);
              if (value == NULL && PyErr_Occurred ())
                {
                  return (*env)->PopLocalFrame (env, NULL);
                }
              (*env)->SetObjectArrayElement (env, jarray, (jsize) i, value);
              (*env)->DeleteLocalRef (env, value);
            }
          return (*env)->PopLocalFrame (env, jarray);
        }
      else if ((*env)->IsSameObject (env, componentType, INT_TYPE))
        {
          pyfastsequence_as_primitive_array (jint, Int)
        }
      else if ((*env)->IsSameObject (env, componentType, FLOAT_TYPE))
        {
          pyfastsequence_as_primitive_array (jfloat, Float)
        }
      else if ((*env)->IsSameObject (env, componentType, DOUBLE_TYPE))
        {
          pyfastsequence_as_primitive_array (jdouble, Double)
        }
      else if ((*env)->IsSameObject (env, componentType, LONG_TYPE))
        {
          pyfastsequence_as_primitive_array (jlong, Long)
        }
      else if ((*env)->IsSameObject (env, componentType, BOOLEAN_TYPE))
        {
          pyfastsequence_as_primitive_array (jboolean, Boolean)
        }
      else if ((*env)->IsSameObject (env, componentType, CHAR_TYPE))
        {
          pyfastsequence_as_primitive_array (jchar, Char)
        }
      else if ((*env)->IsSameObject (env, componentType, BYTE_TYPE))
        {
          pyfastsequence_as_primitive_array (jbyte, Byte)
        }
      else if ((*env)->IsSameObject (env, componentType, SHORT_TYPE))
        {
          pyfastsequence_as_primitive_array (jshort, Short)
        }
    }
  return NULL;
}

jobject pyobject_to_jobject (JNIEnv *env, PyObject *py_object, jclass jcl)
{
  if (py_object == NULL)
    {
      return NULL;
    }
  else if (py_object == Py_None)
    {
      return NULL;
    }
  else if (PyUnicode_Check (py_object))
    {
      return pyunicode_as_jobject (env, py_object, jcl);
    }
  else if (PyBool_Check (py_object))
    {
      return pybool_as_jobject (env, py_object, jcl);
    }
  else if (NpyScalar_Check (py_object))
    {
      return npy_scalar_as_jobject (env, py_object, jcl);
    }
  else if (PyLong_Check (py_object))
    {
      return pylong_as_jobject (env, py_object, jcl);
    }
  else if (PyFloat_Check (py_object))
    {
      return pyfloat_as_jobject (env, py_object, jcl);
    }
  else if (NpyArray_Check (py_object))
    {
      return new_ktndarray (env, (PyArrayObject *) py_object);
    }
  else if (PyList_Check (py_object) || PyTuple_Check (py_object))
    {
      return pyseq_as_jobject (env, py_object, jcl);
    }
  return NULL;
}

