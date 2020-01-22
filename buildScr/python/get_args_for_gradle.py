from distutils import sysconfig
import os
import sys
import json

from utils import get_pylib, get_numpy_include

if __name__ == '__main__':
    args = {}

    if sys.version_info < (3, 5):
        raise RuntimeError('python 3.5 or greater is required.')

    args['inc_python'] = sysconfig.get_python_inc()

    args['inc_numpy'] = get_numpy_include()

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
