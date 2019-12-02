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

#define PY_SSIZE_T_CLEAN
#include <Python.h>

#ifdef WIN
#include "winconfig.h"
#endif

#if HAVE_CONFIG_H
#include <config.h>
#endif

#if HAVE_UNISTD_H
#include <sys/types.h>
#include <unistd.h>
#endif

#include <jni.h>

#ifndef _Included_platforms
#define _Included_platforms

// WIN
#ifdef WIN
#define FILE_SEP               '\\'
#else
#define FILE_SEP               '/'
#endif // WIN

/* Default number local references - 16 (PushLocalFrame)*/
#define JLOCAL_REFS 16

/* Python 2 */
#if PY_MAJOR_VERSION < 3
#define Py_hash_t long
#endif
/* Python 2 */

/* Python 3 */
#if PY_MAJOR_VERSION >= 3

#define Py_TPFLAGS_HAVE_ITER 0

#define PyInt_AsLong(i)                   PyLong_AsLongLong(i)
#define PyInt_AS_LONG(i)                  PyLong_AsLongLong(i)
#define PyInt_Check(i)                    PyLong_Check(i)
#define PyInt_FromLong(i)                 PyLong_FromLongLong(i)

#define PyString_FromString(str)          PyUnicode_FromString(str)
#define PyString_Check(str)               PyUnicode_Check(str)
#define PyString_FromFormat(fmt, ...)     PyUnicode_FromFormat(fmt, ##__VA_ARGS__)

#define PyString_AsString(str)            PyUnicode_AsUTF8(str)
#define PyString_AS_STRING(str)           PyUnicode_AsUTF8(str)
#define PyString_Size(str)                PyUnicode_GetLength(str)
#define PyString_GET_SIZE(str)            PyUnicode_GET_LENGTH(str)

#endif
/* Python 3 */

#endif