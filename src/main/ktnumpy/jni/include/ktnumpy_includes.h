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

#include "platforms.h"

#define NPY_NO_DEPRECATED_API NPY_1_7_API_VERSION
#include "numpy/ndarraytypes.h"
#include "numpy/arrayobject.h"

#include "interpreter.h"
#include "java_convert_to_python.h"
#include "python_convert_to_java.h"
#include "exceptions.h"
#include "util.h"
#include "ktnumpy.h"


// Class
#include "java_classes/Class.h"
#include "java_classes/Number.h"
#include "java_classes/Byte.h"
#include "java_classes/Short.h"
#include "java_classes/Integer.h"
#include "java_classes/Long.h"
#include "java_classes/Float.h"
#include "java_classes/Double.h"
#include "java_classes/Boolean.h"
#include "java_classes/Character.h"
#include "java_classes/Slice.h"
#include "java_classes/None.h"
#include "java_classes/List.h"
#include "java_classes/Collection.h"
#include "java_classes/ArrayList.h"
#include "java_classes/Map.h"
#include "java_classes/StackTraceElement.h"
#include "java_classes/NumKtException.h"
#include "java_classes/Throwable.h"
#include "java_classes/Pair.h"
#include "KtNDArray.h"