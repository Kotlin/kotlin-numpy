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

static jmethodID getStartID = 0;
static jmethodID getStopID = 0;
static jmethodID getStepID = 0;

jobject numkt_core_Slice_getStart (JNIEnv *env, jobject jslice)
{
  if (!JNI_METHOD(getStartID, env, SLICE_TYPE, "getStart", "()Ljava/lang/Integer;"))
    {
      return NULL;
    }
  return (*env)->CallObjectMethod (env, jslice, getStartID);
}

jobject numkt_core_Slice_getStop (JNIEnv *env, jobject jslice)
{
  if (!JNI_METHOD (getStopID, env, SLICE_TYPE, "getStop", "()Ljava/lang/Integer;"))
    {
      return NULL;
    }
  return (*env)->CallObjectMethod (env, jslice, getStopID);
}

jobject numkt_core_Slice_getStep (JNIEnv *env, jobject jslice)
{
  if (!JNI_METHOD (getStepID, env, SLICE_TYPE, "getStep", "()Ljava/lang/Integer;"))
    {
      return NULL;
    }
  return (*env)->CallObjectMethod (env, jslice, getStepID);
}