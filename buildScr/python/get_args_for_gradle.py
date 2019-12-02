from distutils import sysconfig
import numpy
import os
from buildKtNumPy import get_py_lib
import sys

if __name__ == '__main__':
    include_path = [sysconfig.get_python_inc(), os.path.join(numpy.__path__[0], 'core', 'include'),
                    sysconfig.get_config_var('LIBDIR'), get_py_lib()]
    print(" ".join(include_path))
