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

static jmethodID initStackTraceElementID = 0;

jobject
java_lang_StackTraceElement_init (JNIEnv *env, jstring file_no_ext, jstring func_name, jstring file_no_dir, jint line_num)
{
  jobject result = NULL;
  if (JNI_METHOD (initStackTraceElementID, env, STACK_TRACE_ELEMENT_TYPE, "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V"))
    {
      result = (*env)->NewObject (env, STACK_TRACE_ELEMENT_TYPE, initStackTraceElementID, file_no_ext, func_name, file_no_dir, line_num);
    }
  return result;
}