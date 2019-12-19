from distutils import sysconfig
import numpy
import os
import sys

from utils import get_pylib

if __name__ == '__main__':
    args = []

    if sys.version_info < (3, 5):
        raise RuntimeError('python 3.5 or greater is required.')

    args.append(sysconfig.get_python_inc())

    inc_numpy = os.path.join(numpy.__path__[0], 'core', 'include')
    if os.path.exists(inc_numpy):
        args.append(inc_numpy)
    else:
        raise RuntimeError("numpy include package not found in: {0}".format(inc_numpy))

    if 'win32' not in sys.platform:
        args.append(sysconfig.get_config_var('LIBDIR'))
    else:
        libdir = os.path.join(sys.prefix, 'libs')
        if os.path.exists(libdir):
            args.append(libdir)
        else:
            raise RuntimeError("not found LIBDIR")

    args.append(get_pylib())

    print("\n".join(args))
