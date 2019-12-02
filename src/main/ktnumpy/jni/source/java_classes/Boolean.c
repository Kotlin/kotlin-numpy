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

static jmethodID newBooleanID = 0;
static jmethodID booleanValueID = 0;

jobject java_lang_Boolean_new (JNIEnv *env, jboolean z)
{
  if (!JNI_METHOD(newBooleanID, env, BOOLEAN_TYPE, "<init>", "(Z)V")) {
      return NULL;
    }
  return (*env)->NewObject(env, BOOLEAN_TYPE, newBooleanID, z);
}

jboolean java_lang_Boolean_booleanValue (JNIEnv *env, jobject self)
{
  jboolean result = JNI_FALSE;
  if (JNI_METHOD(booleanValueID, env, BOOLEAN_TYPE, "booleanValue", "()Z"))
    {
      result = (*env)->CallBooleanMethod (env, self, booleanValueID);
    }
  return result;
}
