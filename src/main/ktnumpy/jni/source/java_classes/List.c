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
static jmethodID addID = 0;

jobject java_util_List_get (JNIEnv *env, jobject this, jint index)
{
  jobject result = NULL;
  if (JNI_METHOD(getID, env, LIST_TYPE, "get", "(I)Ljava/lang/Object;"))
    {
      result = (*env)->CallObjectMethod (env, this, getID, index);
    }
  return result;
}

jboolean java_util_List_add (JNIEnv *env, jobject this, jobject v)
{
  jboolean result = JNI_FALSE;
  if (JNI_METHOD (addID, env, LIST_TYPE, "add", "(Ljava/lang/Object;)Z"))
    {
      result = (*env)->CallBooleanMethod (env, this, addID, v);
    }
  return result;
}