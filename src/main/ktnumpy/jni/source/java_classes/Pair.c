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

static jmethodID newPairID = 0;
static jmethodID getFirstID = 0;
static jmethodID getSecondID = 0;

jobject kotlin_Pair_new (JNIEnv *env, jobject f, jobject s)
{
  if (!JNI_METHOD(newPairID, env, PAIR_TYPE, "<init>", "(Ljava/lang/Object;Ljava/lang/Object;)V"))
    {
      return NULL;
    }
  return (*env)->NewObject (env, PAIR_TYPE, newPairID, f, s);
}

jobject kotlin_Pair_getFirst (JNIEnv *env, jobject this)
{
  if (!JNI_METHOD (getFirstID, env, PAIR_TYPE, "getFirst", "()Ljava/lang/Object;"))
    {
      return NULL;
    }
  return (*env)->CallObjectMethod (env, this, getFirstID);
}

jobject kotlin_Pair_getSecond (JNIEnv *env, jobject this)
{
  if (!JNI_METHOD (getSecondID, env, PAIR_TYPE, "getSecond", "()Ljava/lang/Object;"))
    {
      return NULL;
    }
  return (*env)->CallObjectMethod (env, this, getSecondID);
}