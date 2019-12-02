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

static jmethodID getComponentType = 0;
static jmethodID isArray = 0;

jclass java_lang_Class_getComponentType (JNIEnv *env, jclass this)
{
  jclass result = NULL;
  if (JNI_METHOD(getComponentType, env, CLASS_TYPE, "getComponentType",
                 "()Ljava/lang/Class;"))
    {
      result = (jclass) (*env)->CallObjectMethod (env, this, getComponentType);
    }
  return result;
}

jboolean java_lang_Class_IsArray (JNIEnv *env, jclass jcl)
{
  jboolean result = JNI_FALSE;
  if (JNI_METHOD (isArray, env, CLASS_TYPE, "isArray", "()Z"))
    {
      result = (*env)->CallBooleanMethod (env, jcl, isArray);
    }
  return result;
}
