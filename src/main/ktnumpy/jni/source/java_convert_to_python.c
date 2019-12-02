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

PyObject *intArray_to_tuple (JNIEnv *env, jintArray arr)
{
  jsize arr_length = 0;
  PyObject *py_tuple = NULL;

  if (arr == NULL)
    {
      Py_RETURN_NONE;
    }

  arr_length = (*env)->GetArrayLength (env, arr);
  jint *jdims = (*env)->GetIntArrayElements (env, arr, 0);
  py_tuple = PyTuple_New (arr_length);
  for (int i = 0; i < arr_length; i++)
    {
      PyTuple_SetItem (py_tuple, i, PyLong_FromLong (jdims[i]));
    }

  (*env)->ReleaseIntArrayElements (env, arr, jdims, JNI_ABORT);

  return py_tuple;
}

PyObject *objArray_to_PyArray (JNIEnv *env, jobjectArray obj_arr, PyObject *dtype)
{
  jsize arr_length = 0;
  PyObject *py_array = NULL;
  if (obj_arr == NULL)
    {
      Py_RETURN_NONE;
    }

  arr_length = (*env)->GetArrayLength (env, obj_arr);
  py_array = PyList_New (arr_length);

  if (dtype == NP_INT8)
    {
      jmethodID jmidByteValue = (*env)->GetMethodID (env, BYTE_TYPE, "byteValue", "()B");
      for (int i = 0; i < arr_length; ++i)
        {
          jobject data = (*env)->GetObjectArrayElement (env, obj_arr, i);
          int8_t val = (*env)->CallByteMethod (env, data, jmidByteValue);
          PyList_SetItem (py_array, i, PyLong_FromLong (val));
          (*env)->DeleteLocalRef (env, data);
        }
    }
  else if (dtype == NP_INT16)
    {
      jmethodID jmidShortValue = (*env)->GetMethodID (env, SHORT_TYPE, "shortValue", "()S");
      for (int i = 0; i < arr_length; ++i)
        {
          jobject data = (*env)->GetObjectArrayElement (env, obj_arr, i);
          int16_t val = (*env)->CallShortMethod (env, data, jmidShortValue);
          PyList_SetItem (py_array, i, PyLong_FromLong (val));
          (*env)->DeleteLocalRef (env, data);
        }
    }
  else if (dtype == NP_INT32)
    {
      jmethodID jmidIntValue = (*env)->GetMethodID (env, INT_TYPE, "intValue", "()I");
      for (int i = 0; i < arr_length; i++)
        {
          jobject data = (*env)->GetObjectArrayElement (env, obj_arr, i);
          int32_t val = (*env)->CallIntMethod (env, data, jmidIntValue);
          PyList_SetItem (py_array, i, PyLong_FromLong (val));
          (*env)->DeleteLocalRef (env, data);
        }
    }
  else if (dtype == NP_INT64)
    {
      jmethodID jmidLongValue = (*env)->GetMethodID (env, LONG_TYPE, "longValue", "()J");
      for (int i = 0; i < arr_length; ++i)
        {
          jobject data = (*env)->GetObjectArrayElement (env, obj_arr, i);
          int64_t val = (*env)->CallLongMethod (env, data, jmidLongValue);
          (*env)->DeleteLocalRef (env, data);
          PyList_SetItem (py_array, i, PyLong_FromLong (val));
        }
    }
  else if (dtype == NP_FLOAT32)
    {
      jmethodID jmidFloatValue = (*env)->GetMethodID (env, FLOAT_TYPE, "floatValue", "()F");
      for (int i = 0; i < arr_length; ++i)
        {
          jobject data = (*env)->GetObjectArrayElement (env, obj_arr, i);
          float val = (*env)->CallFloatMethod (env, data, jmidFloatValue);
          PyList_SetItem (py_array, i, PyFloat_FromDouble (val));
          (*env)->DeleteLocalRef (env, data);
        }
    }
  else if (dtype == NP_FLOAT64)
    {
      jmethodID jmidDoubleValue = (*env)->GetMethodID (env, DOUBLE_TYPE, "doubleValue", "()D");
      for (int i = 0; i < arr_length; ++i)
        {
          jobject data = (*env)->GetObjectArrayElement (env, obj_arr, i);
          double val = (*env)->CallDoubleMethod (env, data, jmidDoubleValue);
          PyList_SetItem (py_array, i, PyFloat_FromDouble (val));
          (*env)->DeleteLocalRef (env, data);
        }
    }
  else if (dtype == NP_BOOL)
    {
      jmethodID jmidBoolValue = (*env)->GetMethodID (env, BOOLEAN_TYPE, "booleanValue", "()Z");
      for (int i = 0; i < arr_length; ++i)
        {
          jobject data = (*env)->GetObjectArrayElement (env, obj_arr, i);
          int val = (*env)->CallBooleanMethod (env, data, jmidBoolValue);
          PyList_SetItem (py_array, i, PyBool_FromLong (val));
          (*env)->DeleteLocalRef (env, data);
        }
    }

  return py_array;
}

PyObject *jobject_to_pyobject (JNIEnv *env, jobject jobj)
{
  PyObject *result = NULL;
  jclass class = NULL;
  if (jobj == NULL)
    {
      return NULL;
    }
  class = (*env)->GetObjectClass (env, jobj);
  if ((*env)->IsSameObject (env, class, NONE_TYPE))
    {
      Py_INCREF(Py_None);
      result = Py_None;
    }
  else if ((*env)->IsSameObject (env, class, CLASS_TYPE))
    {
      result = get_dtype (env, (jclass) jobj);
    }
  else if ((*env)->IsSameObject (env, class, STRING_TYPE))
    {
      result = jstring_AsPyString (env, (jstring) jobj);
    }
  else if ((*env)->IsSameObject (env, class, CHAR_TYPE))
    {
      result = jchar_AsPyObject (env, jobj);
    }
  else if ((*env)->IsAssignableFrom (env, class, NUMBER_TYPE))
    {
      result = jnumber_AsPyObject (env, jobj, class);
    }
  else if ((*env)->IsSameObject (env, class, BOOLEAN_TYPE))
    {
      result = jboolean_AsPyObject (env, jobj);
    }
  else if ((*env)->IsAssignableFrom (env, class, LIST_TYPE))
    {
      result = jlist_AsPyList (env, jobj, class);
    }
  else if ((*env)->IsAssignableFrom (env, class, KTNDARRAY_TYPE))
    {
      result = ktarray_AsPyObject (env, jobj);
    }
  else if ((*env)->IsSameObject (env, class, SLICE_TYPE))
    {
      result = jslice_AsPySlice (env, jobj);
    }
  else
    {
      jboolean is_array = java_lang_Class_IsArray (env, class);
      if ((*env)->ExceptionCheck (env))
        {
          printf ("java_lang_Class_IsArray\n");
          exit (-1);
        }
      else if (is_array)
        {
          result = jarray_AsPyTuple (env, (jobjectArray) jobj, class);
        }
    }

  (*env)->DeleteLocalRef (env, class);
  return result;
}

PyObject *jstring_AsPyString (JNIEnv *env, jstring jstr)
{
  PyObject *result = NULL;
  const jchar *str = (*env)->GetStringChars (env, jstr, 0);
  jsize size = (*env)->GetStringLength (env, jstr);
  result = PyUnicode_DecodeUTF16 ((const char *) str, size * 2, NULL, NULL);
  (*env)->ReleaseStringChars (env, jstr, str);
  return result;
}

PyObject *jchar_AsPyObject (JNIEnv *env, jobject jobj)
{
  jchar ch = java_lang_Character_charValue (env, jobj);
  if ((*env)->ExceptionCheck (env))
    {
      return NULL;
    }

  Py_UCS2 char_val = (Py_UCS2) ch;
  return PyUnicode_FromKindAndData (PyUnicode_2BYTE_KIND, &char_val, 1);
}

PyObject *jnumber_AsPyObject (JNIEnv *env, jobject jobj, jclass jcl)
{
  if ((*env)->IsSameObject (env, jcl, BYTE_TYPE))
    {
      jbyte b = java_lang_Number_byteValue (env, jobj);
      if ((*env)->ExceptionCheck (env))
        {
          return NULL;
        }
      return PyLong_FromLong (b);
    }
  else if ((*env)->IsSameObject (env, jcl, SHORT_TYPE))
    {
      jshort s = java_lang_Number_shortValue (env, jobj);
      if ((*env)->ExceptionCheck (env))
        {
          return NULL;
        }
      return PyLong_FromLong (s);
    }
  else if ((*env)->IsSameObject (env, jcl, INT_TYPE))
    {
      jint i = java_lang_Number_intValue (env, jobj);
      if ((*env)->ExceptionCheck (env))
        {
          return NULL;
        }
      return PyLong_FromLong (i);
    }
  else if ((*env)->IsSameObject (env, jcl, LONG_TYPE))
    {
      jlong j = java_lang_Number_longValue (env, jobj);
      if ((*env)->ExceptionCheck (env))
        {
          return NULL;
        }
      return PyLong_FromLongLong (j);
    }
  else if ((*env)->IsSameObject (env, jcl, FLOAT_TYPE))
    {
      jfloat f = java_lang_Number_floatValue (env, jobj);
      if ((*env)->ExceptionCheck (env))
        {
          return NULL;
        }
      return PyFloat_FromDouble (f);
    }
  else if ((*env)->IsSameObject (env, jcl, DOUBLE_TYPE))
    {
      jdouble d = java_lang_Number_doubleValue (env, jobj);
      if ((*env)->ExceptionCheck (env))
        {
          return NULL;
        }
      return PyFloat_FromDouble (d);
    }
  else
    {
      return NULL;
    }
}

PyObject *jboolean_AsPyObject (JNIEnv *env, jobject jobj)
{
  jboolean b = java_lang_Boolean_booleanValue (env, jobj);
  if ((*env)->ExceptionCheck (env))
    {
      return NULL;
    }
  return PyBool_FromLong (b);
}

PyObject *jlist_AsPyList (JNIEnv *env, jobject jlist, jclass jcl)
{
  jsize list_length = 0;
  PyObject *py_list = NULL;
  jobject val = NULL;
  PyObject *py_val = NULL;

  if (jlist == NULL)
    {
      return NULL;
    }

  list_length = java_util_Collection_size (env, jlist);

  py_list = PyList_New (list_length);

  for (int i = 0; i < list_length; ++i)
    {
      val = java_util_List_get (env, jlist, (jint) i);

      py_val = jobject_to_pyobject (env, val);
      PyList_SetItem (py_list, i, py_val);

      (*env)->DeleteLocalRef (env, val);
    }

  return py_list;
}

PyObject *ktarray_AsPyObject (JNIEnv *env, jobject jobj)
{
  PyObject *res = (PyObject *) numkt_core_KtNDArray_getPointer (env, jobj);
  Py_XINCREF (res);
  return res;
}

PyObject *jslice_AsPySlice (JNIEnv *env, jobject jobj)
{

  return NULL;
}

PyObject *jarray_AsPyTuple (JNIEnv *env, jobjectArray jobj, jclass jcl)
{
  jsize arr_length = 0;
  PyObject *py_tuple = NULL;

  if (jobj == NULL)
    {
      return NULL;
    }

  arr_length = (*env)->GetArrayLength (env, jobj);
  py_tuple = PyTuple_New (arr_length);

  if ((*env)->IsInstanceOf (env, jobj, BOOLEAN_ARRAY_TYPE))
    {
      jboolean *data = (*env)->GetBooleanArrayElements (env, jobj, 0);
      for (int i = 0; i < arr_length; ++i)
        {
          PyTuple_SetItem (py_tuple, i, PyBool_FromLong (data[i]));
        }
      (*env)->ReleaseBooleanArrayElements (env, jobj, data, JNI_ABORT);
    }
  else if ((*env)->IsInstanceOf (env, jobj, BYTE_ARRAY_TYPE))
    {
      jbyte *data = (*env)->GetByteArrayElements (env, jobj, 0);
      for (int i = 0; i < arr_length; ++i)
        {
          PyTuple_SetItem (py_tuple, i, PyLong_FromLong (data[i]));
        }
      (*env)->ReleaseByteArrayElements (env, jobj, data, JNI_ABORT);
    }
  else if ((*env)->IsInstanceOf (env, jobj, SHORT_ARRAY_TYPE))
    {
      jshort *data = (*env)->GetShortArrayElements (env, jobj, 0);
      for (int i = 0; i < arr_length; ++i)
        {
          PyTuple_SetItem (py_tuple, i, PyLong_FromLong (data[i]));
        }
      (*env)->ReleaseShortArrayElements (env, jobj, data, JNI_ABORT);
    }
  else if ((*env)->IsInstanceOf (env, jobj, INT_ARRAY_TYPE))
    {
      jint *data = (*env)->GetIntArrayElements (env, jobj, 0);
      for (int i = 0; i < arr_length; i++)
        {
          PyTuple_SetItem (py_tuple, i, PyLong_FromLong (data[i]));
        }
      (*env)->ReleaseIntArrayElements (env, jobj, data, JNI_ABORT);
    }
  else if ((*env)->IsInstanceOf (env, jobj, LONG_ARRAY_TYPE))
    {
      jlong *data = (*env)->GetLongArrayElements (env, jobj, 0);
      for (int i = 0; i < arr_length; ++i)
        {
          PyTuple_SetItem (py_tuple, i, PyLong_FromLong (data[i]));
        }
      (*env)->ReleaseLongArrayElements (env, jobj, data, JNI_ABORT);
    }
  else if ((*env)->IsInstanceOf (env, jobj, FLOAT_ARRAY_TYPE))
    {
      jfloat *data = (*env)->GetFloatArrayElements (env, jobj, 0);
      for (int i = 0; i < arr_length; ++i)
        {
          PyTuple_SetItem (py_tuple, i, PyFloat_FromDouble (data[i]));
        }
      (*env)->ReleaseFloatArrayElements (env, jobj, data, JNI_ABORT);
    }
  else if ((*env)->IsInstanceOf (env, jobj, DOUBLE_ARRAY_TYPE))
    {
      jdouble *data = (*env)->GetDoubleArrayElements (env, jobj, 0);
      for (int i = 0; i < arr_length; ++i)
        {
          PyTuple_SetItem (py_tuple, i, PyFloat_FromDouble (data[i]));
        }
      (*env)->ReleaseDoubleArrayElements (env, jobj, data, JNI_ABORT);
    }
  else if ((*env)->IsSameObject (env, jobj, CHAR_ARRAY_TYPE))
    {
      jchar *data = (*env)->GetCharArrayElements (env, jobj, 0);
      for (int i = 0; i < arr_length; ++i)
        {
          Py_UCS2 char_val = (Py_UCS2) data[i];
          PyTuple_SetItem (py_tuple, i, PyUnicode_FromKindAndData (PyUnicode_2BYTE_KIND, &char_val, 1));
        }
      (*env)->ReleaseCharArrayElements (env, jobj, data, JNI_ABORT);
    }
  else
    {
      for (int i = 0; i < arr_length; ++i)
        {
          jobject value = (*env)->GetObjectArrayElement (env, jobj, i);
          PyObject *py_val = jobject_to_pyobject (env, value);
          if (NpyArray_Check (py_val))
            {
              Py_IncRef (py_val);
            }
          PyTuple_SetItem (py_tuple, i, py_val);
          (*env)->DeleteLocalRef (env, value);
        }
    }
  return py_tuple;
}
