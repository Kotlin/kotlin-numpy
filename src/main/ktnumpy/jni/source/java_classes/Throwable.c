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

static jmethodID getStackTraceID = 0;
static jmethodID setStackTraceID = 0;

jarray java_lang_Throwable_getStackTrace (JNIEnv *env, jobject this)
{
  jarray result = 0;
    if (JNI_METHOD(getStackTraceID, env, THROWABLE_TYPE, "getStackTrace",
                   "()[Ljava/lang/StackTraceElement;"))
      {
        result = (jarray) (*env)->CallObjectMethod (env, this, getStackTraceID);
      }
  return result;
}

void java_lang_Throwable_setStackTrace (JNIEnv *env, jobject this, jarray stackTrace)
{
    if (JNI_METHOD(setStackTraceID, env, THROWABLE_TYPE, "setStackTrace",
                   "([Ljava/lang/StackTraceElement;)V"))
      {
        (*env)->CallVoidMethod (env, this, setStackTraceID, stackTrace);
      }
}