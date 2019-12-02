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

static jmethodID initNumKtExceptionID = 0;

jobject numkt_NumKtException_init_string (JNIEnv *env, jstring jmessage)
{
  jobject result = NULL;
  if (JNI_METHOD (initNumKtExceptionID, env, NUMKTEXCEPTION_TYPE, "<init>", "(Ljava/lang/String;)V"))
    {
      result = (*env)->NewObject (env, NUMKTEXCEPTION_TYPE, initNumKtExceptionID, jmessage);
    }
  return result;
}