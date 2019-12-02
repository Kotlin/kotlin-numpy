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

#include "platforms.h"

#ifndef _JAVA_CONVERT_TO_PYTHON_H_
#define _JAVA_CONVERT_TO_PYTHON_H_

PyObject *intArray_to_tuple (JNIEnv *, jintArray);
PyObject *objArray_to_PyArray (JNIEnv *, jobjectArray, PyObject *);
PyObject *jobject_to_pyobject(JNIEnv *, jobject);
PyObject *jstring_AsPyString(JNIEnv *, jstring);
PyObject *jchar_AsPyObject(JNIEnv *, jobject);
PyObject *jnumber_AsPyObject(JNIEnv *, jobject, jclass);
PyObject *jboolean_AsPyObject(JNIEnv *, jobject);
PyObject *jlist_AsPyList(JNIEnv *, jobject, jclass);
PyObject *ktarray_AsPyObject(JNIEnv *, jobject);
PyObject *jslice_AsPySlice(JNIEnv *, jobject);
PyObject *jarray_AsPyTuple(JNIEnv *, jobjectArray , jclass);

#endif //_JAVA_CONVERT_TO_PYTHON_H_
