#include "ktnumpy_includes.h"

static jmethodID newKtNDiterID = 0;

typedef struct KtNpyArrayIterObject_tag KtNpyArrayIterObject;

struct KtNpyArrayIterObject_tag {
  PyObject_HEAD
  NpyIter *iter;
  char started, finished;
  NpyIter_IterNextFunc *iternext;
  NpyIter_GetMultiIndexFunc *get_multi_index;
  char **dataptrs;
  PyArray_Descr **dtypes;
};

static PyObject *import (void)
{
  import_array ()
  return NULL;
}

static jobject numkt_core_KtNDiter_new (JNIEnv *env, jlong pointer)
{
  jobject ktnditer_obj = NULL;
  if (!JNI_METHOD (newKtNDiterID, env, KTNDITER_TYPE, "<init>", "(J)V"))
    {
      return NULL;
    }
  ktnditer_obj = (*env)->NewObject (env, LONG_TYPE, newKtNDiterID, pointer);
  if (ktnditer_obj == NULL)
    {
      (*env)->ThrowNew (env, NUMKTEXCEPTION_TYPE, "Error to create new KtNDIter");
      return NULL;
    }
  return ktnditer_obj;
}

static int ktnditer_cache_values (KtNpyArrayIterObject *this)
{
  NpyIter *iter = this->iter;

  this->iternext = NpyIter_GetIterNext (iter, NULL);
  if (this->iternext == NULL)
    {
      return -1;
    }

  if (NpyIter_HasMultiIndex (iter) && !NpyIter_HasDelayedBufAlloc (iter))
    {
      this->get_multi_index = NpyIter_GetGetMultiIndex (iter, NULL);
    }
  else
    {
      this->get_multi_index = NULL;
    }

  /* data pointers */
  this->dataptrs = NpyIter_GetDataPtrArray (iter);
  this->dtypes = NpyIter_GetDescrArray (iter);

  return 0;
}

static int iter_flags_converter (JNIEnv *env, jarray flags_in, npy_uint32 *flags)
{
  npy_uint32 tmpflags = 0;
  int i, n;

  const char *str = NULL;
  npy_uint32 flag = 0;
  jobject f = NULL;

  n = (*env)->GetArrayLength (env, flags_in);
  for (i = 0; i < n; ++i)
    {
      f = (*env)->GetObjectArrayElement (env, flags_in, i);
      str = jstring_to_char (env, f);

      if (strcmp (str, "NPY_ITER_DONT_NEGATE_STRIDES") == 0)
        {
          flag = NPY_ITER_DONT_NEGATE_STRIDES;
        }
      else if (strcmp (str, "NPY_ITER_REFS_OK") == 0)
        {
          flag = NPY_ITER_REFS_OK;
        }
      else if (strcmp (str, "NPY_ITER_ZEROSIZE_OK") == 0)
        {
          flag = NPY_ITER_ZEROSIZE_OK;
        }
      else if (strcmp (str, "NPY_ITER_BUFFERED") == 0)
        {
          flag = NPY_ITER_BUFFERED;
        }
      else if (strcmp (str, "NPY_ITER_DELAY_BUFALLOC") == 0)
        {
          flag = NPY_ITER_DELAY_BUFALLOC;
        }
      tmpflags |= flag;
      release_utf_char (env, f, str);
    }

  tmpflags |= (npy_uint32) NPY_ITER_READONLY;
  tmpflags |= (npy_uint32) NPY_ITER_C_INDEX;
  tmpflags |= (npy_uint32) NPY_ITER_MULTI_INDEX;

  *flags |= tmpflags;
  return 1;
}

static int iter_casting_converter (JNIEnv *env, jstring casting_in, NPY_CASTING *casting)
{
  const char *str = jstring_to_char (env, casting_in);
  if (strcmp (str, "no") == 0)
    {
      *casting = NPY_NO_CASTING;
      return 1;
    }
  else if (strcmp (str, "equiv") == 0)
    {
      *casting = NPY_EQUIV_CASTING;
      return 1;
    }
  else if (strcmp (str, "safe") == 0)
    {
      *casting = NPY_SAFE_CASTING;
      return 1;
    }
  else if (strcmp (str, "same_kind") == 0)
    {
      *casting = NPY_SAME_KIND_CASTING;
      return 1;
    }
  else if (strcmp (str, "unsafe") == 0)
    {
      *casting = NPY_UNSAFE_CASTING;
      return 1;
    }
  else
    {
      (*env)->ThrowNew (env, NUMKTEXCEPTION_TYPE, "Error to convert casting flag");
    }

  return 0;
}

jobject ktnditer_seq_item (JNIEnv *env, KtNpyArrayIterObject *this, size_t i);

/*
 * Class:     org_jetbrains_numkt_core_KtNDIter
 * Method:    iterNew
 * Signature: (Lorg/jetbrains/numkt/core/KtNDArray;[Ljava/lang/String;Ljava/lang/String;)J
 */
JNIEXPORT jlong JNICALL Java_org_jetbrains_numkt_core_KtNDIter_iterNew
    (JNIEnv *env, jobject jobj, jobject kt_arr, jobjectArray flags_in, jstring casting_in)
{
  import ();

  PyArrayObject *arr = numkt_core_KtNDArray_getPointer (env, kt_arr);

  npy_uint32 flags = 0;
  NPY_CASTING casting = NPY_SAFE_CASTING;

  KtNpyArrayIterObject *this = malloc (sizeof (KtNpyArrayIterObject));

  if (!iter_flags_converter (env, flags_in, &flags) || !iter_casting_converter (env, casting_in, &casting))
    {
      return -1;
    }

  this->iter = NpyIter_New (arr, flags, NPY_KEEPORDER, casting, NULL);

  ktnditer_cache_values (this);

  if (NpyIter_GetIterSize (this->iter) == 0)
    {
      this->started = 1;
      this->finished = 1;
    }
  else
    {
      this->started = 0;
      this->finished = 0;
    }

  return (jlong) this;
}

/*
 * Class:     org_jetbrains_numkt_core_KtNDIter
 * Method:    dealloc
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_org_jetbrains_numkt_core_KtNDIter_dealloc
    (JNIEnv *env, jobject jobj, jlong ptr)
{
  KtNpyArrayIterObject *this = (KtNpyArrayIterObject *) ptr;
  PyEval_AcquireThread (mainThreadState);
  if (this->iter)
    {
      NpyIter_Deallocate (this->iter);
      this->iter = NULL;
    }

  free (this);
  PyEval_ReleaseThread (mainThreadState);
}

/*
 * Class:     org_jetbrains_numkt_core_KtNDIter
 * Method:    iterReset
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jetbrains_numkt_core_KtNDIter_iterReset
    (JNIEnv *env, jobject jobj, jlong ptr)
{
  KtNpyArrayIterObject *this = (KtNpyArrayIterObject *) ptr;
  if (this->iter == NULL)
    {
      (*env)->ThrowNew (env, NUMKTEXCEPTION_TYPE, "Iterator is invalid");
      return 0;
    }

  if (NpyIter_Reset (this->iter, NULL) != NPY_SUCCEED)
    {
      return 0;
    }
  if (NpyIter_GetIterSize (this->iter) == 0)
    {
      this->started = 1;
      this->finished = 1;
    }
  else
    {
      this->started = 0;
      this->finished = 0;
    }

  if (this->get_multi_index == NULL && NpyIter_HasMultiIndex (this->iter))
    {
      this->get_multi_index = NpyIter_GetGetMultiIndex (this->iter, NULL);
    }

  return 1;
}

/*
 * Class:     org_jetbrains_numkt_core_KtNDIter
 * Method:    iterNextC
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jetbrains_numkt_core_KtNDIter_iterNextC
    (JNIEnv *env, jobject jobj, jlong ptr)
{
  KtNpyArrayIterObject *this = (KtNpyArrayIterObject *) ptr;
  if (this->iter != NULL && this->iternext != NULL && !this->finished && this->iternext (this->iter))
    {
      return 1;
    }
  else
    {
      this->finished = 1;
      return 0;
    }
}

/*
 * Class:     org_jetbrains_numkt_core_KtNDIter
 * Method:    iterRemoveAxis
 * Signature: (JI)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jetbrains_numkt_core_KtNDIter_iterRemoveAxis
    (JNIEnv *env, jobject jobj, jlong ptr, jint axis)
{
  KtNpyArrayIterObject *this = (KtNpyArrayIterObject *) ptr;

  if (this->iter == NULL)
    {
      (*env)->ThrowNew (env, NUMKTEXCEPTION_TYPE, "Iterator is invalid");
      return 0;
    }

  // parse remove axis

  if (NpyIter_RemoveAxis (this->iter, axis) != NPY_SUCCEED)
    {
      return 0;
    }

  if (ktnditer_cache_values (this) < 0)
    {
      return 0;
    }

  if (NpyIter_GetIterSize (this->iter) == 0)
    {
      this->started = 1;
      this->finished = 1;
    }
  else
    {
      this->started = 0;
      this->finished = 0;
    }

  return 1;
}

/*
 * Class:     org_jetbrains_numkt_core_KtNDIter
 * Method:    iterRemoveMultiIndex
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jetbrains_numkt_core_KtNDIter_iterRemoveMultiIndex
    (JNIEnv *env, jobject jobj, jlong ptr)
{
  KtNpyArrayIterObject *this = (KtNpyArrayIterObject *) ptr;
  if (this->iter == NULL)
    {
      (*env)->ThrowNew (env, NUMKTEXCEPTION_TYPE, "Iterator is invalid");
      return 0;
    }

  NpyIter_RemoveMultiIndex (this->iter);
  ktnditer_cache_values (this);
  if (NpyIter_GetIterSize (this->iter) == 0)
    {
      this->started = 1;
      this->finished = 1;
    }
  else
    {
      this->started = 0;
      this->finished = 0;
    }

  return 1;
}

static int debug_print (jlong ptr)
{
  KtNpyArrayIterObject *this = (KtNpyArrayIterObject *) ptr;
  if (this->iter != NULL)
    {
      NpyIter_DebugPrint (this->iter);
    }
  else
    {
      printf ("Iterator: null\n");
    }

  return 1;
}

/*
 * Class:     org_jetbrains_numkt_core_KtNDIter
 * Method:    iterDebugPrintCritical
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jetbrains_numkt_core_KtNDIter_iterDebugPrintCritical
    (JNIEnv *env, jclass jobj_clazz, jlong ptr)
{
  return debug_print (ptr);
}

/*
 * Class:     org_jetbrains_numkt_core_KtNDIter
 * Method:    iterDebugPrintCritical
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL JavaCritical_org_jetbrains_numkt_core_KtNDIter_iterDebugPrintCritical
    (jlong ptr)
{
  return debug_print (ptr);
}

/*
 * Class:     org_jetbrains_numkt_core_KtNDIter
 * Method:    valueGet
 * Signature: (J)Ljava/lang/Object;
 */
JNIEXPORT jobject JNICALL Java_org_jetbrains_numkt_core_KtNDIter_valueGet
    (JNIEnv *env, jobject jobj, jlong ptr)
{
  KtNpyArrayIterObject *this = (KtNpyArrayIterObject *) ptr;
  jobject ret = NULL;

  ret = ktnditer_seq_item (env, this, 0);
  return ret;
}

/*
 * Class:     org_jetbrains_numkt_core_KtNDIter
 * Method:    nextC
 * Signature: (J)Ljava/lang/Object;
 */
JNIEXPORT jobject JNICALL Java_org_jetbrains_numkt_core_KtNDIter_nextC
    (JNIEnv *env, jobject jobj, jlong ptr)
{
  KtNpyArrayIterObject *this = (KtNpyArrayIterObject *) ptr;

  if (this->iter == NULL || this->iternext == NULL || this->finished)
    {
      return NULL;
    }

  if (this->started)
    {
      if (!this->iternext (this->iter))
        {
          this->finished = 1;
          return NULL;
        }
    }
  this->started = 1;

  return Java_org_jetbrains_numkt_core_KtNDIter_valueGet (env, jobj, ptr);
}

/*
 * Class:     org_jetbrains_numkt_core_KtNDIter
 * Method:    shapeGet
 * Signature: (J)[I
 */
JNIEXPORT jintArray JNICALL Java_org_jetbrains_numkt_core_KtNDIter_shapeGet
    (JNIEnv *env, jobject jobj, jlong ptr)
{
  KtNpyArrayIterObject *this = (KtNpyArrayIterObject *) ptr;
  jintArray ret = NULL;
  npy_intp idim, ndim, shape[NPY_MAXDIMS];
  int *buf;

  if (this->iter == NULL || this->finished)
    {
      (*env)->ThrowNew (env, NUMKTEXCEPTION_TYPE, "Iterator is past the end");
      return NULL;
    }

  if (NpyIter_GetShape (this->iter, shape) == NPY_SUCCEED)
    {
      ndim = NpyIter_GetNDim (this->iter);
      ret = (*env)->NewIntArray (env, ndim);
      if (ret != NULL)
        {
          buf = malloc (ndim * sizeof (jint));
          for (idim = 0; idim < ndim; ++idim)
            {
              buf[idim] = shape[idim];
            }

          (*env)->SetIntArrayRegion (env, ret, 0, ndim, buf);
          free (buf);
          return ret;
        }
    }

  return NULL;
}

/*
 * Class:     org_jetbrains_numkt_core_KtNDIter
 * Method:    multiIndexGet
 * Signature: (J)[I
 */
JNIEXPORT jintArray JNICALL Java_org_jetbrains_numkt_core_KtNDIter_multiIndexGet
    (JNIEnv *env, jobject jobj, jlong ptr)
{
  KtNpyArrayIterObject *this = (KtNpyArrayIterObject *) ptr;
  npy_intp idim, ndim, multi_index[NPY_MAXDIMS];
  jintArray ret;
  int *buf;

  if (this->iter == NULL || this->finished)
    {
      (*env)->ThrowNew (env, NUMKTEXCEPTION_TYPE, "Iterator is past the end");
      return NULL;
    }

  if (this->get_multi_index != NULL)
    {
      ndim = NpyIter_GetNDim (this->iter);
      this->get_multi_index (this->iter, multi_index);
      ret = (*env)->NewIntArray (env, ndim);
      if (ret == NULL)
        {
          return NULL;
        }
      buf = malloc (ndim * sizeof (jint));
      for (idim = 0; idim < ndim; ++idim)
        {
          buf[idim] = multi_index[idim];
        }
      (*env)->SetIntArrayRegion (env, ret, 0, ndim, buf);
      free (buf);
      return ret;
    }
  else
    {
      if (!NpyIter_HasMultiIndex (this->iter))
        {
          (*env)->ThrowNew (env, NUMKTEXCEPTION_TYPE, "Iterator is not tracking a multi-index");
          return NULL;
        }
      else if (NpyIter_HasDelayedBufAlloc (this->iter))
        {
          (*env)->ThrowNew (env, NUMKTEXCEPTION_TYPE, "Iterator construction used delayed buffer allocation, "
                                                      "and no reset has been done yet");
          return NULL;
        }
      else
        {
          (*env)->ThrowNew (env, NUMKTEXCEPTION_TYPE, "Iterator is in an invalid state");
          return NULL;
        }
    }
}

/*
 * Class:     org_jetbrains_numkt_core_KtNDIter
 * Method:    multiIndexSet
 * Signature: (J[I)V
 */
JNIEXPORT void JNICALL Java_org_jetbrains_numkt_core_KtNDIter_multiIndexSet
    (JNIEnv *env, jobject jobj, jlong ptr, jintArray value)
{
  KtNpyArrayIterObject *this = (KtNpyArrayIterObject *) ptr;
  npy_intp idim, ndim, multi_index[NPY_MAXDIMS];

  if (value == NULL)
    {
      (*env)->ThrowNew (env, NUMKTEXCEPTION_TYPE, "Cannot set null value");
      return;
    }
  if (this->iter == NULL)
    {
      (*env)->ThrowNew (env, NUMKTEXCEPTION_TYPE, "Iterator is invalid");
      return;
    }

  if (NpyIter_HasMultiIndex (this->iter))
    {
      ndim = NpyIter_GetNDim (this->iter);
      if ((*env)->GetArrayLength (env, value) != ndim)
        {
          (*env)->ThrowNew (env, NUMKTEXCEPTION_TYPE, "Wrong number of indices");
          return;
        }
      int *v = (*env)->GetIntArrayElements (env, value, 0);
      for (idim = 0; idim < ndim; ++idim)
        {
          multi_index[idim] = v[idim];
        }
      (*env)->ReleaseIntArrayElements (env, value, v, JNI_ABORT);

      if (NpyIter_GotoMultiIndex (this->iter, multi_index) != NPY_SUCCEED)
        {
          return;
        }
      this->started = 0;
      this->finished = 0;

      return;
    }
  else
    {
      (*env)->ThrowNew (env, NUMKTEXCEPTION_TYPE, "Iterator is not tracking a multi-index");
      return;
    }
}

/*
 * Class:     org_jetbrains_numkt_core_KtNDIter
 * Method:    indexGet
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_org_jetbrains_numkt_core_KtNDIter_indexGet
    (JNIEnv *env, jobject jobj, jlong ptr)
{
  KtNpyArrayIterObject *this = (KtNpyArrayIterObject *) ptr;

  if (this->iter == NULL || this->finished)
    {
      (*env)->ThrowNew (env, NUMKTEXCEPTION_TYPE, "Iterator is past the end");
      return -1;
    }

  if (NpyIter_HasIndex (this->iter))
    {
      npy_intp ind = *NpyIter_GetIndexPtr (this->iter);
      return ind;
    }
  else
    {
      (*env)->ThrowNew (env, NUMKTEXCEPTION_TYPE, "Iterator does not have an index");
      return -1;
    }
}

/*
 * Class:     org_jetbrains_numkt_core_KtNDIter
 * Method:    indexSet
 * Signature: (JI)V
 */
JNIEXPORT void JNICALL Java_org_jetbrains_numkt_core_KtNDIter_indexSet
    (JNIEnv *env, jobject jobj, jlong ptr, jint value)
{
  KtNpyArrayIterObject *this = (KtNpyArrayIterObject *) ptr;

  if (this->iter == NULL)
    {
      (*env)->ThrowNew (env, NUMKTEXCEPTION_TYPE, "Iterator is invalid");
      return;
    }

  if (NpyIter_HasIndex (this->iter))
    {
      if (NpyIter_GotoIndex (this->iter, value) != NPY_SUCCEED)
        {
          return;
        }
      this->started = 0;
      this->finished = 0;

      return;
    }
  else
    {
      (*env)->ThrowNew (env, NUMKTEXCEPTION_TYPE, "Iterator does not have an index");
      return;
    }
}

/*
 * Class:     org_jetbrains_numkt_core_KtNDIter
 * Method:    iterIndexGet
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_org_jetbrains_numkt_core_KtNDIter_iterIndexGet
    (JNIEnv *env, jobject jobj, jlong ptr)
{
  KtNpyArrayIterObject *this = (KtNpyArrayIterObject *) ptr;
  if (this->iter == NULL || this->finished)
    {
      (*env)->ThrowNew (env, NUMKTEXCEPTION_TYPE, "Iterator is past the end");
      return -1;
    }

  return NpyIter_GetIterIndex (this->iter);
}

/*
 * Class:     org_jetbrains_numkt_core_KtNDIter
 * Method:    iterIndexSet
 * Signature: (JI)V
 */
JNIEXPORT void JNICALL Java_org_jetbrains_numkt_core_KtNDIter_iterIndexSet
    (JNIEnv *env, jobject jobj, jlong ptr, jint iterindex)
{
  KtNpyArrayIterObject *this = (KtNpyArrayIterObject *) ptr;
  if (this->iter == NULL)
    {
      (*env)->ThrowNew (env, NUMKTEXCEPTION_TYPE, "Iterator is invalid");
      return;
    }

  if (NpyIter_GotoIterIndex (this->iter, iterindex) != NPY_SUCCEED)
    {
      return;
    }
  this->started = 0;
  this->finished = 0;

}


/*
 * Class:     org_jetbrains_numkt_core_KtNDIter
 * Method:    iterRangeGet
 * Signature: (J)Ljava/lang/Object;
 */
JNIEXPORT jobject JNICALL Java_org_jetbrains_numkt_core_KtNDIter_iterRangeGet
    (JNIEnv *env, jobject jobj, jlong ptr)
{
  KtNpyArrayIterObject *this = (KtNpyArrayIterObject *) ptr;
  npy_intp istart = 0, iend = 0;

  if (this->iter == NULL)
    {
      (*env)->ThrowNew (env, NUMKTEXCEPTION_TYPE, "Iterator is invalid");
      return NULL;
    }

  NpyIter_GetIterIndexRange (this->iter, &istart, &iend);

  return kotlin_Pair_new (env, java_lang_Integer_new (env, istart), java_lang_Integer_new (env, iend));
}

/*
 * Class:     org_jetbrains_numkt_core_KtNDIter
 * Method:    iterRangeSet
 * Signature: (JLjava/lang/Object;)V
 */
JNIEXPORT void JNICALL Java_org_jetbrains_numkt_core_KtNDIter_iterRangeSet
    (JNIEnv *env, jobject jobj, jlong ptr, jobject value)
{
  KtNpyArrayIterObject *this = (KtNpyArrayIterObject *) ptr;
  npy_intp istart = 0, iend = 0;

  if (value == NULL)
    {
      (*env)->ThrowNew (env, NUMKTEXCEPTION_TYPE, "Cannot set iterRange");
      return;
    }
  if (this->iter == NULL)
    {
      (*env)->ThrowNew (env, NUMKTEXCEPTION_TYPE, "Iterator is invalid");
      return;
    }

  istart = java_lang_Number_intValue (env, kotlin_Pair_getFirst (env, value));
  iend = java_lang_Number_intValue (env, kotlin_Pair_getSecond (env, value));

  if (NpyIter_ResetToIterIndexRange (this->iter, istart, iend, NULL) != NPY_SUCCEED)
    {
      return;
    }

  if (istart < iend)
    {
      this->started = this->finished = 0;
    }
  else
    {
      this->started = this->finished = 1;
    }

  if (this->get_multi_index == NULL && NpyIter_HasMultiIndex (this->iter))
    {
      this->get_multi_index = NpyIter_GetGetMultiIndex (this->iter, NULL);
    }

}

/*
 * Class:     org_jetbrains_numkt_core_KtNDIter
 * Method:    ndimGet
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_org_jetbrains_numkt_core_KtNDIter_ndimGet
    (JNIEnv *env, jobject jobj, jlong ptr)
{
  KtNpyArrayIterObject *this = (KtNpyArrayIterObject *) ptr;
  if (this->iter == NULL)
    {
      (*env)->ThrowNew (env, NUMKTEXCEPTION_TYPE, "Iterator is invalid");
      return 0;
    }

  return NpyIter_GetNDim (this->iter);
}

/*
 * Class:     org_jetbrains_numkt_core_KtNDIter
 * Method:    iterSizeGet
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_org_jetbrains_numkt_core_KtNDIter_iterSizeGet
    (JNIEnv *env, jobject jobj, jlong ptr)
{
  KtNpyArrayIterObject *this = (KtNpyArrayIterObject *) ptr;
  if (this->iter == NULL)
    {
      (*env)->ThrowNew (env, NUMKTEXCEPTION_TYPE, "Iterator is invalid");
      return 0;
    }

  return NpyIter_GetIterSize (this->iter);
}

static int finished (long ptr)
{
  KtNpyArrayIterObject *this = (KtNpyArrayIterObject *) ptr;
  if (this->iter == NULL || !this->finished)
    {
      return 0;
    }
  else
    {
      return 1;
    }
}

/*
 * Class:     org_jetbrains_numkt_core_KtNDIter
 * Method:    finishedGetCritical
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jetbrains_numkt_core_KtNDIter_finishedGetCritical
    (JNIEnv *env, jclass jobj_clazz, jlong ptr)
{
  return finished (ptr);
}

/*
 * Class:     org_jetbrains_numkt_core_KtNDIter
 * Method:    finishedGetCritical
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL JavaCritical_org_jetbrains_numkt_core_KtNDIter_finishedGetCritical
    (jlong ptr)
{
  return finished (ptr);
}

/*
 * Class:     org_jetbrains_numkt_core_KtNDIter
 * Method:    iterClose
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_org_jetbrains_numkt_core_KtNDIter_iterClose
    (JNIEnv *env, jobject jobj, jlong ptr)
{
  KtNpyArrayIterObject *this = (KtNpyArrayIterObject *) ptr;
  NpyIter *iter = this->iter;
  int ret;
  if (this->iter == NULL)
    {
      return;
    }
  ret = NpyIter_Deallocate (iter);
  this->iter = NULL;
  if (ret < 0)
    {
      (*env)->ThrowNew (env, NUMKTEXCEPTION_TYPE, "Error dealloc");
    }
}

jobject ktnditer_seq_item (JNIEnv *env, KtNpyArrayIterObject *this, size_t i)
{
  npy_intp nop;
  char *dataptr;
  PyArray_Descr *dtype;
  jobject ret;

  if (this->iter == NULL || this->finished)
    {
      (*env)->ThrowNew (env, NUMKTEXCEPTION_TYPE, "Iterator is past the end");
      return NULL;
    }

  if (NpyIter_HasDelayedBufAlloc (this->iter))
    {
      (*env)->ThrowNew (env, NUMKTEXCEPTION_TYPE, "Iterator construction used delayed buffer allocation, "
                                                  "and no reset has been done yet");
      return NULL;
    }

  nop = NpyIter_GetNOp (this->iter);
  // Negative indexing
  if (i < 0)
    {
      i += nop;
    }

  if (i < 0 || i >= nop)
    {
      (*env)->ThrowNew (env, NUMKTEXCEPTION_TYPE, "Iterator index is out of bounds");
      return NULL;
    }
  dataptr = this->dataptrs[i];
  dtype = this->dtypes[i];

  switch (dtype->type_num)
    {
  case 1:
    {
      ret = java_lang_Byte_new (env, *(jbyte *) dataptr);
      break;
    }
  case 3:
    {
      ret = java_lang_Short_new (env, *(jshort *) dataptr);
      break;
    }
  case 5:
    {
      ret = java_lang_Integer_new (env, *(jint *) dataptr);
      break;
    }
  case 7:
    {
      ret = java_lang_Long_new (env, *(jlong *) dataptr);
      break;
    }
  case 11:
    {
      ret = java_lang_Float_new (env, *(jfloat *) dataptr);
      break;
    }
  case 12:
    {
      ret = java_lang_Double_new (env, *(jdouble *) dataptr);
      break;
    }
  case 19:
    {
      ret = java_lang_Character_new (env, *(jchar *) dataptr);
      break;
    }
  case 0:
    {
      ret = java_lang_Boolean_new (env, *(jboolean *) dataptr);
      break;
    }
  default:
    {
      (*env)->ThrowNew (env, NUMKTEXCEPTION_TYPE, "Unknown type");
      return NULL;
    }
    }
  Py_INCREF (dtype);
  return ret;
}
