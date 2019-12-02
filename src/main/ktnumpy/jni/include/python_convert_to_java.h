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

#ifndef _PYTHON_CONVERT_TO_JAVA_H_
#define _PYTHON_CONVERT_TO_JAVA_H_

jboolean PyObject_As_jboolean (PyObject *);

jbyte PyObject_As_jbyte (PyObject *);
jshort PyObject_As_jshort (PyObject *);
jint PyObject_As_jint (PyObject *);
jlong PyObject_As_jlong (PyObject *);

jfloat PyObject_As_jfloat (PyObject *);
jdouble PyObject_As_jdouble (PyObject *);

jchar PyObject_As_jchar (PyObject *);

jstring PyObject_As_jstring (JNIEnv *, PyObject *);

jobject pyobject_to_jobject (JNIEnv *, PyObject *, jclass);

#endif //_PYTHON_CONVERT_TO_JAVA_H_
