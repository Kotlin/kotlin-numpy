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

#ifndef _KTNUMPY_H_
#define _KTNUMPY_H_

int ktnumpy_init (JNIEnv *);

int NpyArray_Check (PyObject *);
int NpyScalar_Check (PyObject *);

jobject npy_scalar_as_jobject (JNIEnv *, PyObject *, jclass);

jobject npy_scalar_to_jobject (JNIEnv *, PyObject *);

jobject get_bytebuffer (JNIEnv *, PyArrayObject *);

jintArray get_shape (JNIEnv *, PyArrayObject *);
jobject get_ndim (JNIEnv *, PyArrayObject *);
jobject get_itemsize (JNIEnv *, PyArrayObject *);
jobject get_size (JNIEnv *, PyArrayObject *);
jintArray get_strides (JNIEnv *, PyArrayObject *);
jobject get_jdtype (JNIEnv *, PyArrayObject *);

PyObject *get_dtype (JNIEnv *, jclass);

jobject get_value (JNIEnv *, PyObject *, jlongArray);
jobject get_ndvalue (JNIEnv *, PyObject *, jobjectArray);

void set_value (JNIEnv *, PyObject *, jlongArray, jobject);
void set_ndvalue (JNIEnv *, PyObject *, jobjectArray, jobject);

jobject
invoke_call_function (JNIEnv *, jobjectArray, jobjectArray, jobject);
jobject
invoke_call_function_with_class (JNIEnv *, jobjectArray, jobjectArray, jobject, jclass);

#endif //_KTNUMPY_H_
