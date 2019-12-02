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

static jmethodID getID = 0;
static jmethodID containsKeyID = 0;

jboolean java_util_Map_containsKey (JNIEnv *env, jobject this, jobject key)
{
  jboolean result = JNI_FALSE;
    if (JNI_METHOD(containsKeyID, env, MAP_TYPE, "containsKey", "(Ljava/lang/Object;)Z"))
      {
        result = (*env)->CallBooleanMethod (env, this, containsKeyID, key);
      }
  return result;
}

jobject java_util_Map_get (JNIEnv *env, jobject this, jobject key)
{
  jobject result = NULL;
    if (JNI_METHOD (getID, env, MAP_TYPE, "get", "(Ljava/lang/Object;)Ljava/lang/Object;"))
      {
        result = (*env)->CallObjectMethod (env, this, getID, key);
      }
  return result;
}