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

#ifndef _UTIL_H_
#define _UTIL_H_

#define JNI_METHOD(var, env, type, name, sig)\
  ((var) || ((var) = (*(env))-> GetMethodID(env, type, name, sig)))

#define JAVA_CLASS_TABLE(F)                                   \
  F(OBJECT_TYPE, "java/lang/Object")                          \
  F(CLASS_TYPE, "java/lang/Class")                            \
  F(BYTE_TYPE, "java/lang/Byte")                              \
  F(SHORT_TYPE, "java/lang/Short")                            \
  F(INT_TYPE, "java/lang/Integer")                            \
  F(LONG_TYPE, "java/lang/Long")                              \
  F(FLOAT_TYPE, "java/lang/Float")                            \
  F(DOUBLE_TYPE, "java/lang/Double")                          \
  F(BOOLEAN_TYPE, "java/lang/Boolean")                        \
  F(CHAR_TYPE, "java/lang/Character")                         \
  F(KTNDARRAY_TYPE, "org/jetbrains/numkt/core/KtNDArray")                   \
  F(STRING_TYPE, "java/lang/String")                          \
  F(NUMBER_TYPE, "java/lang/Number")                          \
  F(COLLECTION_TYPE, "java/util/Collection")                  \
  F(LIST_TYPE, "java/util/List")                              \
  F(ARRAYLIST_TYPE, "java/util/ArrayList")                    \
  F(MAP_TYPE, "java/util/Map")                                \
  F(HASHMAP_TYPE, "java/util/HashMap")                        \
  F(SLICE_TYPE, "org/jetbrains/numkt/core/Slice")                           \
  F(NONE_TYPE, "org/jetbrains/numkt/core/None")                             \
  F(NUMKTEXCEPTION_TYPE, "org/jetbrains/numkt/NumKtException")              \
  F(THROWABLE_TYPE, "java/lang/Throwable")                    \
  F(STACK_TRACE_ELEMENT_TYPE, "java/lang/StackTraceElement")  \
  F(PAIR_TYPE, "kotlin/Pair")                                 \

#define DEFINE_JAVA_CLASS_GLOBAL(var, name) extern jclass var;
JAVA_CLASS_TABLE(DEFINE_JAVA_CLASS_GLOBAL)

#define PYTHON_DTYPE(F)     \
  F(NP_INT8, "int8")        \
  F(NP_INT16, "int16")      \
  F(NP_INT32, "int32")      \
  F(NP_INT64, "int64")      \
  F(NP_FLOAT32, "float32")  \
  F(NP_FLOAT64, "float64")  \
  F(NP_BOOL, "bool")        \
  F(NP_UNICODE, "unicode")  \

#define DEFINE_PYTHON_DTYPE(var, name) extern PyObject *(var);
PYTHON_DTYPE (DEFINE_PYTHON_DTYPE)

extern jclass PR_BOOLEAN_TYPE;
extern jclass PR_BYTE_TYPE;
extern jclass PR_SHORT_TYPE;
extern jclass PR_INT_TYPE;
extern jclass PR_LONG_TYPE;
extern jclass PR_FLOAT_TYPE;
extern jclass PR_DOUBLE_TYPE;
extern jclass PR_CHAR_TYPE;

extern jclass BOOLEAN_ARRAY_TYPE;
extern jclass BYTE_ARRAY_TYPE;
extern jclass SHORT_ARRAY_TYPE;
extern jclass INT_ARRAY_TYPE;
extern jclass LONG_ARRAY_TYPE;
extern jclass FLOAT_ARRAY_TYPE;
extern jclass DOUBLE_ARRAY_TYPE;
extern jclass CHAR_ARRAY_TYPE;

const char *jstring_to_char (JNIEnv *, jstring);
void release_utf_char (JNIEnv *, jstring, const char *);
PyObject *jclass_to_dtype (JNIEnv *, jclass jcl);
int cache_java_class (JNIEnv *);
int cache_primitive_jarrays (JNIEnv *);
int cache_python_dtype (PyObject *);

#endif //_UTIL_H_