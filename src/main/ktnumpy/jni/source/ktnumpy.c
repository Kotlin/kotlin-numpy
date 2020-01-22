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
#include "stdio.h"

PyObject *sysModule, *npModule, *py_Func, *dtypeFunc;

static PyObject *_init_np (void)
{
  import_array ()
  return NULL;
}

int ktnumpy_init (JNIEnv *env)
{

  if (cache_java_class (env))
    {
      fprintf (stderr, "Error get java and kotlin type.\n");
      exit (-1);
    }

  if (cache_primitive_jarrays (env))
    {
      fprintf (stderr, "Error get java arrays.\n");
      exit (-1);
    }

  sysModule = PyImport_ImportModule ("sys");
  if (sysModule == NULL)
    {
      return python_exception (env);
    }

  npModule = PyImport_ImportModule ("numpy");
  if (npModule == NULL)
    {
      return python_exception (env);
    }

  dtypeFunc = PyObject_GetAttrString (npModule, "dtype");
  if (dtypeFunc == NULL)
    {
      return python_exception (env);
    }

  _init_np ();

  if (cache_python_dtype (npModule))
    {
      return python_exception (env);
    }

  return 0;
}

int NpyArray_Check (PyObject *py_object)
{
  return PyArray_Check (py_object);
}

int NpyScalar_Check (PyObject *py_object)
{
  return PyArray_IsScalar (py_object, Number) || PyArray_IsScalar(py_object, Bool);
}

jobject npy_scalar_as_jobject (JNIEnv *env, PyObject *py_object, jclass clazz)
{
  jobject result = NULL;

  if (PyArray_IsScalar (py_object, Int8))
    {
      npy_int8 b;
      if ((*env)->IsAssignableFrom (env, BYTE_TYPE, clazz))
        {
          PyArray_ScalarAsCtype (py_object, &b);
          result = java_lang_Byte_new (env, (jbyte) b);
        }
      else if ((*env)->IsAssignableFrom (env, SHORT_TYPE, clazz))
        {
          PyArray_ScalarAsCtype (py_object, &b);
          result = java_lang_Short_new (env, (jshort) b);
        }
      else if ((*env)->IsAssignableFrom (env, INT_TYPE, clazz))
        {
          PyArray_ScalarAsCtype (py_object, &b);
          result = java_lang_Integer_new (env, (jint) b);
        }
      else if ((*env)->IsAssignableFrom (env, LONG_TYPE, clazz))
        {
          PyArray_ScalarAsCtype (py_object, &b);
          result = java_lang_Long_new (env, (jlong) b);
        }
    }
  else if (PyArray_IsScalar (py_object, Int16))
    {
      npy_int16 s;
      if ((*env)->IsAssignableFrom (env, SHORT_TYPE, clazz))
        {
          PyArray_ScalarAsCtype (py_object, &s);
          result = java_lang_Short_new (env, (jshort) s);
        }
      else if ((*env)->IsAssignableFrom (env, INT_TYPE, clazz))
        {
          PyArray_ScalarAsCtype (py_object, &s);
          result = java_lang_Integer_new (env, (jint) s);
        }
      else if ((*env)->IsAssignableFrom (env, LONG_TYPE, clazz))
        {
          PyArray_ScalarAsCtype (py_object, &s);
          result = java_lang_Long_new (env, (jlong) s);
        }
      else if ((*env)->IsAssignableFrom (env, BYTE_TYPE, clazz))
        {
          PyArray_ScalarAsCtype (py_object, &s);
          result = java_lang_Byte_new (env, (jbyte) s);
        }
    }
  else if (PyArray_IsScalar (py_object, Int32))
    {
      npy_int32 i;

      if ((*env)->IsAssignableFrom (env, INT_TYPE, clazz))
        {
          PyArray_ScalarAsCtype (py_object, &i);
          result = java_lang_Integer_new (env, (jint) i);
        }
      else if ((*env)->IsAssignableFrom (env, LONG_TYPE, clazz))
        {
          PyArray_ScalarAsCtype (py_object, &i);
          result = java_lang_Long_new (env, (jlong) i);
        }
      else if ((*env)->IsAssignableFrom (env, SHORT_TYPE, clazz))
        {
          PyArray_ScalarAsCtype (py_object, &i);
          result = java_lang_Short_new (env, (jshort) i);
        }
      else if ((*env)->IsAssignableFrom (env, BYTE_TYPE, clazz))
        {
          PyArray_ScalarAsCtype (py_object, &i);
          result = java_lang_Byte_new (env, (jbyte) i);
        }
    }
  else if (PyArray_IsScalar (py_object, Int64))
    {
      npy_int64 j;
      if ((*env)->IsAssignableFrom (env, LONG_TYPE, clazz))
        {
          PyArray_ScalarAsCtype (py_object, &j);
          result = java_lang_Long_new (env, (jlong) j);
        }
      else if ((*env)->IsAssignableFrom (env, INT_TYPE, clazz))
        {
          PyArray_ScalarAsCtype (py_object, &j);
          result = java_lang_Integer_new (env, (jint) j);
        }
      else if ((*env)->IsAssignableFrom (env, SHORT_TYPE, clazz))
        {
          PyArray_ScalarAsCtype (py_object, &j);
          result = java_lang_Short_new (env, (jshort) j);
        }
      else if ((*env)->IsAssignableFrom (env, BYTE_TYPE, clazz))
        {
          PyArray_ScalarAsCtype (py_object, &j);
          result = java_lang_Byte_new (env, (jbyte) j);
        }
    }
  else if (PyArray_IsScalar (py_object, Float32))
    {
      npy_float32 f;
      if ((*env)->IsAssignableFrom (env, FLOAT_TYPE, clazz))
        {
          PyArray_ScalarAsCtype (py_object, &f);
          result = java_lang_Float_new (env, (jfloat) f);
        }
      else if ((*env)->IsAssignableFrom (env, DOUBLE_TYPE, clazz))
        {
          PyArray_ScalarAsCtype (py_object, &f);
          result = java_lang_Double_new (env, (jdouble) f);
        }
    }
  else if (PyArray_IsScalar (py_object, Float64))
    {
      npy_float64 d;
      if ((*env)->IsAssignableFrom (env, DOUBLE_TYPE, clazz))
        {
          PyArray_ScalarAsCtype (py_object, &d);
          result = java_lang_Double_new (env, (jdouble) d);
        }
      else if ((*env)->IsAssignableFrom (env, FLOAT_TYPE, clazz))
        {
          PyArray_ScalarAsCtype (py_object, &d);
          result = java_lang_Float_new (env, (jfloat) d);
        }
    }
  else if (PyArray_IsScalar (py_object, Bool))
    {
      npy_bool z;
      if ((*env)->IsAssignableFrom (env, BOOLEAN_TYPE, clazz))
        {
          PyArray_ScalarAsCtype (py_object, &z);
          result = java_lang_Boolean_new (env, (jboolean) z);
        }
      else if ((*env)->IsAssignableFrom (env, BYTE_TYPE, clazz))
        {
          PyArray_ScalarAsCtype (py_object, &z);
          result = java_lang_Byte_new (env, (jbyte) z);
        }
    }

  python_exception (env);

  return result;
}

jobject npy_scalar_to_jobject (JNIEnv *env, PyObject *py_object)
{
  jobject result = NULL;

  if (PyArray_IsScalar(py_object, Int8))
    {
      npy_int8 b;
      PyArray_ScalarAsCtype (py_object, &b);
      result = java_lang_Byte_new (env, (jbyte) b);
    }
  else if (PyArray_IsScalar(py_object, Int16))
    {
      npy_int16 s;
      PyArray_ScalarAsCtype (py_object, &s);
      result = java_lang_Short_new (env, (jshort) s);
    }
  else if (PyArray_IsScalar(py_object, Int32))
    {
      npy_int32 i;
      PyArray_ScalarAsCtype (py_object, &i);
      result = java_lang_Integer_new (env, (jint) i);
    }
  else if (PyArray_IsScalar(py_object, Int64))
    {
      npy_int64 j;
      PyArray_ScalarAsCtype (py_object, &j);
      result = java_lang_Long_new (env, (jlong) j);
    }
  else if (PyArray_IsScalar(py_object, Float32))
    {
      npy_float32 f;
      PyArray_ScalarAsCtype (py_object, &f);
      result = java_lang_Float_new (env, (jfloat) f);
    }
  else if (PyArray_IsScalar(py_object, Float64))
    {
      npy_float64 d;
      PyArray_ScalarAsCtype (py_object, &d);
      result = java_lang_Double_new (env, (jdouble) d);
    }
  return result;
}

jobject get_bytebuffer (JNIEnv *env, PyArrayObject *nparray)
{
  const char *address = NULL;
  jlong size_n_bytes = 0;
  jobject bufferRef = NULL;

  address = PyArray_BYTES (nparray);

  if (PyArray_BASE (nparray) == NULL)
    {
      size_n_bytes = PyArray_NBYTES(nparray);
    }
  else
    {
      for (int i = 0; i < PyArray_NDIM (nparray); ++i)
        {
          size_n_bytes += PyArray_STRIDE (nparray, i) * PyArray_DIM (nparray, i);
        }
      if (size_n_bytes < 0)
        {
          address = PyArray_BYTES ((PyArrayObject *) PyArray_BASE (nparray));
          size_n_bytes = PyArray_NBYTES((PyArrayObject *) PyArray_BASE (nparray));
        }
    }

  jobject directBuffer = (*env)->NewDirectByteBuffer (env, (void *) address, size_n_bytes);
  bufferRef = (*env)->NewWeakGlobalRef (env, directBuffer);

  return bufferRef;
}

jintArray get_shape (JNIEnv *env, PyArrayObject *ndarray)
{
  int size = PyArray_NDIM (ndarray);
  int *buf = malloc (size * sizeof (jint));
  jintArray jArray = (*env)->NewIntArray (env, size);

  if (jArray == NULL)
    {
      free (buf);
      return (*env)->PopLocalFrame (env, NULL);
    }

  npy_intp *shape = PyArray_SHAPE (ndarray);

  for (int i = 0; i < size; ++i)
    {
      buf[i] = shape[i];
    }

  (*env)->SetIntArrayRegion (env, jArray, 0, size, buf);
  return jArray;
}

jobject get_ndim (JNIEnv *env, PyArrayObject *ndarray)
{
  jobject res = NULL;
  jint i = PyArray_NDIM (ndarray);

  res = java_lang_Integer_new (env, i);
  if (!res)
    {
      printf ("Warning: to create new Integer value!\n");
    }

  return res;
}

jobject get_itemsize (JNIEnv *env, PyArrayObject *ndarray)
{
  jobject res = NULL;
  jint i = PyArray_ITEMSIZE (ndarray);

  res = java_lang_Integer_new (env, i);
  if (!res)
    {
      printf ("Warning: to create new Integer value!\n");
    }

  return res;
}

jobject get_size (JNIEnv *env, PyArrayObject *ndarray)
{
  jobject res = NULL;
  jlong j = PyArray_SIZE (ndarray);

  res = java_lang_Long_new (env, j);
  if (!res)
    {
      printf ("Warning: to create new Integer value!\n");
    }

  return res;
}

jintArray get_strides (JNIEnv *env, PyArrayObject *ndarray)
{
  int size = PyArray_NDIM (ndarray);
  int *buf = malloc (size * sizeof (jint));
  jintArray jArray = (*env)->NewIntArray (env, size);

  if (jArray == NULL)
    {
      free (buf);
      return (*env)->PopLocalFrame (env, NULL);
    }

  npy_intp *shape = PyArray_STRIDES (ndarray);

  for (int i = 0; i < size; ++i)
    {
      buf[i] = shape[i];
    }

  (*env)->SetIntArrayRegion (env, jArray, 0, size, buf);
  return jArray;
}

jobject get_jdtype (JNIEnv *env, PyArrayObject *ndarray)
{
  jobject res = NULL;
  switch (PyArray_DTYPE (ndarray)->type_num)
    {
  case NPY_INT8: res = BYTE_TYPE;
      break;
  case NPY_INT16: res = SHORT_TYPE;
      break;
  case NPY_INT32: res = INT_TYPE;
      break;
  case NPY_INT64: res = LONG_TYPE;
      break;
  case NPY_FLOAT32: res = FLOAT_TYPE;
      break;
  case NPY_FLOAT64: res = DOUBLE_TYPE;
      break;
  case NPY_BOOL: res = BOOLEAN_TYPE;
      break;
  case NPY_UNICODE: res = CHAR_TYPE;
      break;
  default: printf ("Error: dtype to java_class\n");
    }
  return res;
}

PyObject *get_dtype (JNIEnv *env, jclass clazz)
{
  PyObject *nptype = jclass_to_dtype (env, clazz);
  return PyObject_CallFunctionObjArgs (dtypeFunc, nptype, NULL);
}

jobject get_value (JNIEnv *env, PyObject *ndarray, jlongArray jlong_array)
{
  jobject result = NULL;
  jlong *ind = (*env)->GetLongArrayElements (env, jlong_array, 0);

  int t = PyArray_TYPE ((PyArrayObject *) ndarray);
  switch (t)
    {
  case NPY_BYTE:
    {
      npy_int8 b = *(npy_int8 *) PyArray_GetPtr ((PyArrayObject *) ndarray, (const npy_intp *) ind);
      result = java_lang_Byte_new (env, (jbyte) b);
      break;
    }
  case NPY_SHORT:
    {
      npy_int16 s = *(npy_int16 *) PyArray_GetPtr ((PyArrayObject *) ndarray, (const npy_intp *) ind);
      result = java_lang_Short_new (env, (jshort) s);
      break;
    }
  case NPY_INT:
    {
      npy_int32 i = *(npy_int32 *) PyArray_GetPtr ((PyArrayObject *) ndarray, (const npy_intp *) ind);
      result = java_lang_Integer_new (env, (jint) i);
      break;
    }
  case NPY_LONG:
    {
      npy_int64 j = *(npy_int64 *) PyArray_GetPtr ((PyArrayObject *) ndarray, (const npy_intp *) ind);
      result = java_lang_Long_new (env, (jlong) j);
      break;
    }
  case NPY_FLOAT:
    {
      npy_float32 f = *(npy_float32 *) PyArray_GetPtr ((PyArrayObject *) ndarray, (const npy_intp *) ind);
      result = java_lang_Float_new (env, (jfloat) f);
      break;
    }
  case NPY_DOUBLE:
    {
      npy_float64 d = *(npy_float64 *) PyArray_GetPtr ((PyArrayObject *) ndarray, (const npy_intp *) ind);
      result = java_lang_Double_new (env, (jdouble) d);
      break;
    }
  case NPY_BOOL:
    {
      npy_bool z = *(npy_bool *) PyArray_GetPtr ((PyArrayObject *) ndarray, (const npy_intp *) ind);
      result = java_lang_Boolean_new (env, (jboolean) z);
      break;
    }
  case NPY_UNICODE:
    {
      npy_char c = *(npy_char *) PyArray_GetPtr ((PyArrayObject *) ndarray, (const npy_intp *) ind);
      result = java_lang_Character_new (env, (jchar) c);
      break;
    }
  default: printf ("Get value: Unknown type!\n");
    }

  (*env)->ReleaseLongArrayElements (env, jlong_array, ind, JNI_ABORT);

  return result;
}

jobject get_ndvalue (JNIEnv *env, PyObject *ndarray, jobjectArray jobject_array)
{
  PyObject *py_tuple = NULL;
  PyObject *py_slice = NULL;
  PyObject *py_start = NULL;
  PyObject *py_stop = NULL;
  PyObject *py_step = NULL;
  PyObject *py_res = NULL;
  jsize arr_length = 0;
  jobject result = NULL;

  arr_length = (*env)->GetArrayLength (env, jobject_array);
  py_tuple = PyTuple_New (arr_length);

  for (int i = 0; i < arr_length; ++i)
    {
      jobject jslice = (*env)->GetObjectArrayElement (env, jobject_array, i);
      py_start = jobject_to_pyobject (env, numkt_core_Slice_getStart (env, jslice));
      py_stop = jobject_to_pyobject (env, numkt_core_Slice_getStop (env, jslice));
      py_step = jobject_to_pyobject (env, numkt_core_Slice_getStep (env, jslice));

      py_slice = PySlice_New (py_start, py_stop, py_step);

      PyTuple_SetItem (py_tuple, i, py_slice);

      (*env)->DeleteLocalRef (env, jslice);
    }

  py_res = PyObject_GetItem (ndarray, py_tuple);
  if (!python_exception (env) && py_res != NULL)
    {
      result = new_ktndarray (env, (PyArrayObject *) py_res);
    }

  Py_XDECREF (py_tuple);
  Py_XDECREF (py_slice);
  Py_XDECREF (py_start);
  Py_XDECREF (py_stop);
  Py_XDECREF (py_step);

  return result;
}

void set_value (JNIEnv *env, PyObject *ndarray, jlongArray jlong_array, jobject element)
{
  PyObject *py_val = NULL;
  PyObject *py_tuple = NULL;
  jsize arr_length = 0;

  arr_length = (*env)->GetArrayLength (env, jlong_array);
  jlong *ind = (*env)->GetLongArrayElements (env, jlong_array, 0);

  py_tuple = PyTuple_New (arr_length);
  for (int i = 0; i < arr_length; ++i)
    {
      PyTuple_SetItem (py_tuple, i, PyLong_FromLong (ind[i]));
    }

  py_val = jobject_to_pyobject (env, element);

  if (PyObject_SetItem (ndarray, py_tuple, py_val))
    {
      python_exception (env);
    }

  (*env)->ReleaseLongArrayElements (env, jlong_array, ind, JNI_ABORT);

  Py_XDECREF (py_tuple);
}

void set_ndvalue (JNIEnv *env, PyObject *ndarray, jobjectArray jobject_array, jobject element)
{
  PyObject *py_tuple = NULL;
  PyObject *py_slice = NULL;
  PyObject *py_start = NULL;
  PyObject *py_stop = NULL;
  PyObject *py_step = NULL;
  PyObject *py_val = NULL;
  jsize arr_length = 0;

  arr_length = (*env)->GetArrayLength (env, jobject_array);
  py_tuple = PyTuple_New (arr_length);

  for (int i = 0; i < arr_length; ++i)
    {
      jobject jslice = (*env)->GetObjectArrayElement (env, jobject_array, i);
      py_start = jobject_to_pyobject (env, numkt_core_Slice_getStart (env, jslice));
      py_stop = jobject_to_pyobject (env, numkt_core_Slice_getStop (env, jslice));
      py_step = jobject_to_pyobject (env, numkt_core_Slice_getStep (env, jslice));

      py_slice = PySlice_New (py_start, py_stop, py_step);

      PyTuple_SetItem (py_tuple, i, py_slice);

      (*env)->DeleteLocalRef (env, jslice);
    }

  py_val = jobject_to_pyobject (env, element);

  if (PyObject_SetItem (ndarray, py_tuple, py_val))
    {
      python_exception (env);
    }

  Py_XDECREF (py_tuple);
  Py_XDECREF (py_slice);
  Py_XDECREF (py_start);
  Py_XDECREF (py_stop);
  Py_XDECREF (py_step);
}

jobject
invoke_call_function
    (JNIEnv *env, jobjectArray arr_names_func, jobjectArray args, jobject kwargs)
{
  py_Func = NULL;
  PyArrayObject *nparray = NULL;
  PyObject *tmpModule = npModule;
  PyObject *name_func = NULL;
  PyObject *name_mod = NULL;
  PyObject *py_args = NULL;
  PyObject *py_kwargs = NULL;

  jobject name = NULL;
  jobject result = NULL;


  // import modules and functions
  jsize length = (*env)->GetArrayLength (env, arr_names_func);
  if (length > 1)
    {
      for (size_t i = 0; i < length - 1; --i)
        {
          name = (*env)->GetObjectArrayElement (env, arr_names_func, i);
          name_mod = jstring_AsPyString (env, name);
          tmpModule = PyObject_GetAttr (tmpModule, name_mod);
          (*env)->DeleteLocalRef (env, name);
          Py_XDECREF (name_mod);
        }
    }
  name = (*env)->GetObjectArrayElement (env, arr_names_func, length - 1);
  name_func = jstring_AsPyString (env, name);
  py_Func = PyObject_GetAttr (tmpModule, name_func);
  if (python_exception (env) || py_Func == NULL)
    {
      goto OUT;
    }
  (*env)->DeleteLocalRef (env, name);
  Py_XDECREF (name_func);

  // *args
  if (args != NULL)
    {
      jsize args_length = (*env)->GetArrayLength (env, args);
      py_args = PyTuple_New (args_length);

      for (int i = 0; i < args_length; ++i)
        {
          jobject arg = (*env)->GetObjectArrayElement (env, args, i);
          PyObject *py_arg = jobject_to_pyobject (env, arg);
          PyTuple_SetItem (py_args, i, py_arg);
          (*env)->DeleteLocalRef (env, arg);
        }
    }

  // Map kwargs to dict (**kwargs)
  if (kwargs != NULL)
    {
      py_kwargs = PyDict_New ();
      if (java_util_Map_containsKey (env, kwargs, (*env)->NewStringUTF (env, "out")))
        {
          PyObject *out = jobject_to_pyobject (env, java_util_Map_get (env, kwargs, (*env)->NewStringUTF (env, "out")));
          PyDict_SetItemString (py_kwargs, "out", out);
        }
      if (java_util_Map_containsKey (env, kwargs, (*env)->NewStringUTF (env, "where")))
        {
          PyObject *where = jobject_to_pyobject (env, java_util_Map_get (env, kwargs, (*env)->NewStringUTF (env, "where")));
          PyDict_SetItemString (py_kwargs, "where", where);
        }
      if (java_util_Map_containsKey (env, kwargs, (*env)->NewStringUTF (env, "axes")))
        {
          PyObject *axes = jobject_to_pyobject (env, java_util_Map_get (env, kwargs, (*env)->NewStringUTF (env, "axes")));
          PyDict_SetItemString (py_kwargs, "axes", axes);
        }
      if (java_util_Map_containsKey (env, kwargs, (*env)->NewStringUTF (env, "axis")))
        {
          PyObject *axis = jobject_to_pyobject (env, java_util_Map_get (env, kwargs, (*env)->NewStringUTF (env, "axis")));
          PyDict_SetItemString (py_kwargs, "axis", axis);
        }
      if (java_util_Map_containsKey (env, kwargs, (*env)->NewStringUTF (env, "keepdims")))
        {
          PyObject *keepdims = jobject_to_pyobject (env, java_util_Map_get (env, kwargs, (*env)->NewStringUTF (env, "keepdims")));
          PyDict_SetItemString (py_kwargs, "keepdims", keepdims);
        }
      if (java_util_Map_containsKey (env, kwargs, (*env)->NewStringUTF (env, "casting")))
        {
          PyObject *casting = jobject_to_pyobject (env, java_util_Map_get (env, kwargs, (*env)->NewStringUTF (env, "casting")));
          PyDict_SetItemString (py_kwargs, "casting", casting);
        }
      if (java_util_Map_containsKey (env, kwargs, (*env)->NewStringUTF (env, "order")))
        {
          PyObject *order = jobject_to_pyobject (env, java_util_Map_get (env, kwargs, (*env)->NewStringUTF (env, "order")));
          PyDict_SetItemString (py_kwargs, "order", order);
        }
      if (java_util_Map_containsKey (env, kwargs, (*env)->NewStringUTF (env, "dtype")))
        {
          PyObject *dtype = jobject_to_pyobject (env, java_util_Map_get (env, kwargs, (*env)->NewStringUTF (env, "dtype")));
          PyDict_SetItemString (py_kwargs, "dtype", dtype);
        }
      if (java_util_Map_containsKey (env, kwargs, (*env)->NewStringUTF (env, "subok")))
        {
          PyObject *subok = jobject_to_pyobject (env, java_util_Map_get (env, kwargs, (*env)->NewStringUTF (env, "subok")));
          PyDict_SetItemString (py_kwargs, "subok", subok);
        }
    }

  //call func
  nparray = (PyArrayObject *) PyObject_Call (py_Func, py_args, py_kwargs);
  if (python_exception (env) || nparray == NULL)
    {
      goto OUT;
    }

  result = new_ktndarray (env, nparray);

  // goto for exit
  OUT:
  Py_XDECREF (py_Func);
  Py_XDECREF (py_args);
  Py_XDECREF (py_kwargs);

  return result;
}

jobject
invoke_call_function_with_class
    (JNIEnv *env, jobjectArray arr_names_func, jobjectArray args, jobject kwargs, jclass clazz)
{
  py_Func = NULL;
  PyObject *py_res = NULL;
  PyObject *tmpModule = npModule;
  PyObject *name_func = NULL;
  PyObject *name_mod = NULL;
  PyObject *py_kwargs = NULL;
  PyObject *py_args = NULL;

  jobject name = NULL;
  jobject result = NULL;


  // import modules and functions
  jsize length = (*env)->GetArrayLength (env, arr_names_func);
  if (length > 1)
    {
      for (size_t i = 0; i < length - 1; --i)
        {
          name = (*env)->GetObjectArrayElement (env, arr_names_func, i);
          name_mod = jstring_AsPyString (env, name);
          tmpModule = PyObject_GetAttr (tmpModule, name_mod);
          (*env)->DeleteLocalRef (env, name);
          Py_XDECREF (name_mod);
        }
    }
  name = (*env)->GetObjectArrayElement (env, arr_names_func, length - 1);
  name_func = jstring_AsPyString (env, name);
  py_Func = PyObject_GetAttr (tmpModule, name_func);
  if (python_exception (env) || py_Func == NULL)
    {
      goto OUT;
    }
  (*env)->DeleteLocalRef (env, name);
  Py_XDECREF (name_func);

  // *args
  if (args != NULL)
    {
      jsize args_length = (*env)->GetArrayLength (env, args);
      py_args = PyTuple_New (args_length);

      for (int i = 0; i < args_length; ++i)
        {
          jobject arg = (*env)->GetObjectArrayElement (env, args, i);
          PyObject *py_arg = jobject_to_pyobject (env, arg);
          PyTuple_SetItem (py_args, i, py_arg);
          (*env)->DeleteLocalRef (env, arg);
        }
    }

  // Map kwargs to dict kwargs (**kwargs)
  if (kwargs != NULL)
    {
      py_kwargs = PyDict_New ();
      if (java_util_Map_containsKey (env, kwargs, (*env)->NewStringUTF (env, "out")))
        {
          PyObject *out = jobject_to_pyobject (env, java_util_Map_get (env, kwargs, (*env)->NewStringUTF (env, "out")));
          PyDict_SetItemString (py_kwargs, "out", out);
        }
      if (java_util_Map_containsKey (env, kwargs, (*env)->NewStringUTF (env, "where")))
        {
          PyObject *where = jobject_to_pyobject (env, java_util_Map_get (env, kwargs, (*env)->NewStringUTF (env, "where")));
          PyDict_SetItemString (py_kwargs, "where", where);
        }
      if (java_util_Map_containsKey (env, kwargs, (*env)->NewStringUTF (env, "axes")))
        {
          PyObject *axes = jobject_to_pyobject (env, java_util_Map_get (env, kwargs, (*env)->NewStringUTF (env, "axes")));
          PyDict_SetItemString (py_kwargs, "axes", axes);
        }
      if (java_util_Map_containsKey (env, kwargs, (*env)->NewStringUTF (env, "axis")))
        {
          PyObject *axis = jobject_to_pyobject (env, java_util_Map_get (env, kwargs, (*env)->NewStringUTF (env, "axis")));
          PyDict_SetItemString (py_kwargs, "axis", axis);
        }
      if (java_util_Map_containsKey (env, kwargs, (*env)->NewStringUTF (env, "keepdims")))
        {
          PyObject *keepdims = jobject_to_pyobject (env, java_util_Map_get (env, kwargs, (*env)->NewStringUTF (env, "keepdims")));
          PyDict_SetItemString (py_kwargs, "keepdims", keepdims);
        }
      if (java_util_Map_containsKey (env, kwargs, (*env)->NewStringUTF (env, "casting")))
        {
          PyObject *casting = jobject_to_pyobject (env, java_util_Map_get (env, kwargs, (*env)->NewStringUTF (env, "casting")));
          PyDict_SetItemString (py_kwargs, "casting", casting);
        }
      if (java_util_Map_containsKey (env, kwargs, (*env)->NewStringUTF (env, "order")))
        {
          PyObject *order = jobject_to_pyobject (env, java_util_Map_get (env, kwargs, (*env)->NewStringUTF (env, "order")));
          PyDict_SetItemString (py_kwargs, "order", order);
        }
      if (java_util_Map_containsKey (env, kwargs, (*env)->NewStringUTF (env, "dtype")))
        {
          PyObject *dtype = jobject_to_pyobject (env, java_util_Map_get (env, kwargs, (*env)->NewStringUTF (env, "dtype")));
          PyDict_SetItemString (py_kwargs, "dtype", dtype);
        }
      if (java_util_Map_containsKey (env, kwargs, (*env)->NewStringUTF (env, "subok")))
        {
          PyObject *subok = jobject_to_pyobject (env, java_util_Map_get (env, kwargs, (*env)->NewStringUTF (env, "subok")));
          PyDict_SetItemString (py_kwargs, "subok", subok);
        }
    }

  //call func
  py_res = PyObject_Call (py_Func, py_args, py_kwargs);
  if (python_exception (env))
    {
      goto OUT;
    }

  result = pyobject_to_jobject (env, py_res, clazz);

  // goto for exit
  OUT:
  Py_XDECREF (py_Func);
  Py_XDECREF (py_kwargs);
  Py_XDECREF (py_args);

  return result;
}
