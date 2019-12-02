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

static jmethodID newCharID = 0;
static jmethodID charValueID = 0;

jobject java_lang_Character_new (JNIEnv *env, jchar c)
{
  if (!JNI_METHOD(newCharID, env, CHAR_TYPE, "<init>", "(C)V"))
    {
      return NULL;
    }
  return (*env)->NewObject (env, CHAR_TYPE, newCharID, c);
}

jchar java_lang_Character_charValue (JNIEnv *env, jobject this)
{
  jchar result = 0;
  if (JNI_METHOD(charValueID, env, CHAR_TYPE, "charValue", "()C"))
    {
      result = (*env)->CallCharMethod (env, this, charValueID);
    }
  return result;
}