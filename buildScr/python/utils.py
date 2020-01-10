import sys
from distutils import sysconfig


def get_pylib():
    ldver = sysconfig.get_config_var('LDVERSION')
    if ldver:
        version = ldver
    else:
        version = sysconfig.get_config_var('VERSION')
    return 'python' + version


def get_ldlib():
    return sysconfig.get_config_var('LDLIBRARY')


def get_pylib_path():
    if sys.platform.startswith("win32"):
        stdlib = get_python_home() + '\\' + get_pylib() + '.dll'
    elif sys.platform.startswith('darwin'):
        stdlib = sysconfig.get_config_var('LIBDIR') + '/lib' + get_pylib() + '.dylib'
    elif sys.platform.startswith('linux'):
        stdlib = sysconfig.get_config_var('LIBDIR') + '/lib' + get_pylib() + '.so'
    else:
        raise RuntimeError('Unknown platform.')
    return stdlib


def get_python_home():
    return sysconfig.get_config_var('prefix')
