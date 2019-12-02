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

static jmethodID initID = 0;

jobject java_util_ArrayList_new (JNIEnv *env, jint size)
{
  jobject result = NULL;
  if (JNI_METHOD (initID, env, ARRAYLIST_TYPE, "<init>", "(I)V"))
    {
      result = (*env)->NewObject (env, ARRAYLIST_TYPE, initID, size);
    }
  return result;
}