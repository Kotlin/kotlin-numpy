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

#define DEFINE_JAVA_CLASS_VAR(var, name) jclass var = NULL;
JAVA_CLASS_TABLE(DEFINE_JAVA_CLASS_VAR)

#define DEFINE_PYTHON_DTYPE_VAR(var, name) PyObject *var = NULL;
PYTHON_DTYPE (DEFINE_PYTHON_DTYPE_VAR)

jclass PR_BOOLEAN_TYPE = NULL;
jclass PR_BYTE_TYPE = NULL;
jclass PR_SHORT_TYPE = NULL;
jclass PR_INT_TYPE = NULL;
jclass PR_LONG_TYPE = NULL;
jclass PR_FLOAT_TYPE = NULL;
jclass PR_DOUBLE_TYPE = NULL;
jclass PR_CHAR_TYPE = NULL;

jclass BOOLEAN_ARRAY_TYPE = NULL;
jclass BYTE_ARRAY_TYPE = NULL;
jclass SHORT_ARRAY_TYPE = NULL;
jclass INT_ARRAY_TYPE = NULL;
jclass LONG_ARRAY_TYPE = NULL;
jclass FLOAT_ARRAY_TYPE = NULL;
jclass DOUBLE_ARRAY_TYPE = NULL;
jclass CHAR_ARRAY_TYPE = NULL;

const char *jstring_to_char (JNIEnv *env, jstring jstr)
{
  if (jstr == NULL)
    {
      return NULL;
    }
  return (*env)->GetStringUTFChars (env, jstr, 0);
}

void release_utf_char (JNIEnv *env, jstring jstr, const char *ch)
{
  if (ch != NULL && jstr != NULL)
    {
      (*env)->ReleaseStringUTFChars (env, jstr, ch);
      (*env)->DeleteLocalRef (env, jstr);
    }
}

PyObject *jclass_to_dtype (JNIEnv *env, jclass jcl)
{
  if ((*env)->IsAssignableFrom (env, BYTE_TYPE, jcl))
    {
      return NP_INT8;
    }
  else if ((*env)->IsAssignableFrom (env, SHORT_TYPE, jcl))
    {
      return NP_INT16;
    }
  else if ((*env)->IsAssignableFrom (env, INT_TYPE, jcl))
    {
      return NP_INT32;
    }
  else if ((*env)->IsAssignableFrom (env, LONG_TYPE, jcl))
    {
      return NP_INT64;
    }
  else if ((*env)->IsAssignableFrom (env, FLOAT_TYPE, jcl))
    {
      return NP_FLOAT32;
    }
  else if ((*env)->IsAssignableFrom (env, DOUBLE_TYPE, jcl))
    {
      return NP_FLOAT64;
    }
  else if (((*env)->IsAssignableFrom (env, BOOLEAN_TYPE, jcl)))
    {
      return NP_BOOL;
    }
  else if (((*env)->IsAssignableFrom (env, CHAR_TYPE, jcl)))
    {
      return NP_UNICODE;
    }
  else
    {
      printf ("Error: Unknown type!\n");
      exit (-1);
    }
}

#define CACHE_CLASS(var, name)                  \
    if((var) == NULL)                           \
      {                                         \
        jcl = (*env)->FindClass(env, name);     \
        if((*env)->ExceptionCheck(env))         \
            return -1;                          \
        (var) = (*env)->NewGlobalRef(env, jcl); \
        (*env)->DeleteLocalRef(env, jcl);       \
      }                                         \

#define CACHE_DTYPE(var, name)                        \
  if((var) == NULL)                                   \
    {                                                 \
      (var) = PyObject_GetAttrString(npModule, name); \
      if ((var) == NULL)                              \
        return -1;                                    \
    }                                                 \


#define CACHE_PRIMITIVE_ARRAY(component_type, array, name)    \
    if ((component_type) == NULL)                             \
      {                                                       \
        if ((array) == NULL)                                  \
          {                                                   \
            clazz = (*env)->FindClass (env, name);            \
            if ((*env)->ExceptionCheck (env))                 \
              return 1;                                       \
            (array) = (*env)->NewGlobalRef (env, clazz);      \
            (*env)->DeleteLocalRef (env, clazz);              \
          }                                                   \
        clazz = java_lang_Class_getComponentType (env, array);\
        if ((*env)->ExceptionCheck (env))                     \
          return 0;                                           \
        (component_type) = (*env)->NewGlobalRef (env, clazz); \
        (*env)->DeleteLocalRef (env, clazz);                  \
      }                                                       \


int cache_java_class (JNIEnv *env)
{
  jclass jcl;

  JAVA_CLASS_TABLE (CACHE_CLASS)

  return 0;
}

int cache_primitive_jarrays (JNIEnv *env)
{
  jclass clazz = NULL;

  CACHE_PRIMITIVE_ARRAY(PR_BOOLEAN_TYPE, BOOLEAN_ARRAY_TYPE, "[Z")
  CACHE_PRIMITIVE_ARRAY(PR_BYTE_TYPE, BYTE_ARRAY_TYPE, "[B")
  CACHE_PRIMITIVE_ARRAY(PR_SHORT_TYPE, SHORT_ARRAY_TYPE, "[S")
  CACHE_PRIMITIVE_ARRAY(PR_INT_TYPE, INT_ARRAY_TYPE, "[I")
  CACHE_PRIMITIVE_ARRAY(PR_LONG_TYPE, LONG_ARRAY_TYPE, "[J")
  CACHE_PRIMITIVE_ARRAY(PR_FLOAT_TYPE, FLOAT_ARRAY_TYPE, "[F")
  CACHE_PRIMITIVE_ARRAY(PR_DOUBLE_TYPE, DOUBLE_ARRAY_TYPE, "[D")
  CACHE_PRIMITIVE_ARRAY(PR_CHAR_TYPE, CHAR_ARRAY_TYPE, "[C")

  return 0;
}

int cache_python_dtype (PyObject *npModule)
{
  PYTHON_DTYPE (CACHE_DTYPE)

  return 0;
}