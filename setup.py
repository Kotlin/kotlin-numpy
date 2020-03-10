#!/usr/bin/env python

import os
import os.path

# from distutils.core import setup, Extension
from setuptools import setup, Extension, find_packages

from buildScr.python.build_lib import build_ktlib
from buildScr.python.utils import get_pylib, get_python_lib_link, get_java_includes, get_numpy_include

CLASSIFIERS = """\
Development Status :: 3 - Alpha
Intended Audience :: Science/Research
Intended Audience :: Developers
License :: OSI Approved :: Apache Software License
Programming Language :: Java
Programming Language :: C
Programming Language :: Python
Programming Language :: Python :: 3
Programming Language :: Python :: 3.5
Programming Language :: Python :: 3.6
Programming Language :: Python :: 3.7
Programming Language :: Python :: Implementation :: CPython
Topic :: Software Development
Topic :: Scientific/Engineering
Operating System :: Microsoft :: Windows
Operating System :: POSIX :: Linux
Operating System :: MacOS
"""


def get_src() -> list:
    sources = []
    for path, dirs, files in os.walk('src'):
        for f in files:
            if f.endswith('.c'):
                sources.append(os.path.join(path, f))
    return sources


def get_version() -> str:
    with open('gradle.properties') as f:
        for line in f:
            if line.startswith('version='):
                return line[8:].strip()


if __name__ == '__main__':
    setup(name='ktnumpy',
          version=get_version(),
          description='Kotlin bindings for Numpy',
          author='JetBrains',
          url='https://github.com/Kotlin/kotlin-numpy',
          license='Apache 2.0',
          packages=find_packages(),
          classifiers=[_f for _f in CLASSIFIERS.split('\n') if _f],
          platforms=["Windows", "Linux", "Mac OS-X"],
          install_requires=["numpy>=1.15"],
          ext_modules=[
              Extension(
                  name='ktnumpy',
                  sources=get_src(),
                  libraries=[get_pylib()],
                  extra_link_args=get_python_lib_link(),
                  include_dirs=get_java_includes() + ['src/main/ktnumpy/jni/include', get_numpy_include()]
              )
          ],
          cmdclass={
              'build_ext': build_ktlib
          }
          )
