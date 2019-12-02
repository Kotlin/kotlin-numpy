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

static jmethodID byteValueID = 0;
static jmethodID shortValueID = 0;
static jmethodID intValueID = 0;
static jmethodID longValueID = 0;
static jmethodID floatValueID = 0;
static jmethodID doubleValueID = 0;

jbyte java_lang_Number_byteValue (JNIEnv *env, jobject self)
{
  jbyte result = 0;
  if (JNI_METHOD(byteValueID, env, NUMBER_TYPE, "byteValue", "()B"))
    {
      result = (*env)->CallByteMethod (env, self, byteValueID);
    }
  return result;
}

jshort java_lang_Number_shortValue (JNIEnv *env, jobject self)
{
  jshort result = 0;
  if (JNI_METHOD(shortValueID, env, NUMBER_TYPE, "shortValue", "()S"))
    {
      result = (*env)->CallShortMethod (env, self, shortValueID);
    }
  return result;
}

jint java_lang_Number_intValue (JNIEnv *env, jobject self)
{
  jint result = 0;
  if (JNI_METHOD(intValueID, env, NUMBER_TYPE, "intValue", "()I"))
    {
      result = (*env)->CallIntMethod (env, self, intValueID);
    }
  return result;
}

jlong java_lang_Number_longValue (JNIEnv *env, jobject self)
{
  jlong result = 0;
  if (JNI_METHOD(longValueID, env, NUMBER_TYPE, "longValue", "()J"))
    {
      result = (*env)->CallLongMethod (env, self, longValueID);
    }
  return result;
}

jfloat java_lang_Number_floatValue (JNIEnv *env, jobject self)
{
  jfloat result = 0;
  if (JNI_METHOD(floatValueID, env, NUMBER_TYPE, "floatValue", "()F"))
    {
      result = (*env)->CallFloatMethod (env, self, floatValueID);
    }
  return result;
}

jdouble java_lang_Number_doubleValue (JNIEnv *env, jobject self)
{
  jdouble result = 0;
  if (JNI_METHOD(doubleValueID, env, NUMBER_TYPE, "doubleValue", "()D"))
    {
      result = (*env)->CallDoubleMethod (env, self, doubleValueID);
    }
  return result;
}
