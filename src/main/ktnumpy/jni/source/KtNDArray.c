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

static jmethodID newKtNDArrayID = 0;
static jmethodID getPointerID = 0;
static jmethodID getScalarID = 0;

jobject new_ktndarray (JNIEnv *env, PyArrayObject *nparray, jobject scalar)
{
  jobject jbytebuffer = NULL;
  jobject ktndarray = NULL;
  jlong p = 0;

  if (!JNI_METHOD(newKtNDArrayID, env, KTNDARRAY_TYPE, "<init>", "(JLjava/nio/ByteBuffer;Ljava/lang/Object;J)V"))
    {
      return NULL;
    }

  if (nparray)
    {
      if (NpyView_Check (nparray))
        {
          jbytebuffer = get_bytebuffer (env, (PyArrayObject *) PyArray_BASE (nparray));
          p = get_point (nparray);
        }
      else
        {
          jbytebuffer = get_bytebuffer (env, nparray);
        }

      if (jbytebuffer == NULL)
        {
          printf ("Error jbytebuffer");
          exit (-1);
        }
    }

  ktndarray = (*env)->NewObject (env, KTNDARRAY_TYPE, newKtNDArrayID, (jlong) nparray, jbytebuffer, scalar, p);
  if (ktndarray == NULL)
    {
      printf ("Error to create new KtNDArray!\n");
      exit (-1);
    }

  return ktndarray;
}

PyArrayObject *numkt_core_KtNDArray_getPointer (JNIEnv *env, jobject ktndarray)
{
  if (!JNI_METHOD(getPointerID, env, KTNDARRAY_TYPE, "getPointer", "()J"))
    {
      return NULL;
    }
  return (PyArrayObject *) (*env)->CallLongMethod (env, ktndarray, getPointerID);
}

jobject numkt_core_KtNDArray_getScalar (JNIEnv *env, jobject ktndarray)
{
  if (!JNI_METHOD (getScalarID, env, KTNDARRAY_TYPE, "getScalar", "()Ljava/lang/Object;"))
    {
      return NULL;
    }
  return (*env)->CallObjectMethod (env, ktndarray, getScalarID);
}