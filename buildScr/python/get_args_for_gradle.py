from distutils import sysconfig
import numpy
import os
import sys
import json

from utils import get_pylib

if __name__ == '__main__':
    args = {}

    if sys.version_info < (3, 5):
        raise RuntimeError('python 3.5 or greater is required.')

    args['inc_python'] = sysconfig.get_python_inc()

    inc_numpy = os.path.join(numpy.__path__[0], 'core', 'include')
    if os.path.exists(inc_numpy):
        args['inc_numpy'] = inc_numpy
    else:
        raise RuntimeError("numpy include package not found in: {0}".format(inc_numpy))

    if 'win32' not in sys.platform:
        args['libdir'] = sysconfig.get_config_var('LIBDIR')
    else:
        libdir = os.path.join(sys.prefix, 'libs')
        if os.path.exists(libdir):
            args['libdir'] = libdir
        else:
            raise RuntimeError("not found LIBDIR")

    args['pylib'] = get_pylib()

    print(json.dumps(args, sort_keys=True, indent=4))
