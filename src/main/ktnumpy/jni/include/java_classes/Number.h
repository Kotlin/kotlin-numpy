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

#ifndef _NUMBER_H_
#define _NUMBER_H_

jbyte java_lang_Number_byteValue (JNIEnv *, jobject);
jshort java_lang_Number_shortValue (JNIEnv *, jobject);
jint java_lang_Number_intValue (JNIEnv *, jobject);
jlong java_lang_Number_longValue (JNIEnv *, jobject);
jfloat java_lang_Number_floatValue (JNIEnv *, jobject);
jdouble java_lang_Number_doubleValue (JNIEnv *, jobject);

#endif //_NUMBER_H_
