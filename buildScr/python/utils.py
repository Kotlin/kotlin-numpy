import sys
import os
from distutils import sysconfig


class ConfigError(Exception):
    def __init__(self, message):
        super(ConfigError, self).__init__(message)


def is_windows() -> bool:
    return sys.platform.startswith("win32")


def is_linux() -> bool:
    return sys.platform.startswith("linux")


def is_osx() -> bool:
    return sys.platform.startswith("darwin")


def get_pylib() -> str:
    ldver = sysconfig.get_config_var('LDVERSION')
    if ldver:
        version = ldver
    else:
        version = sysconfig.get_config_var('VERSION')
    return 'python' + version


def get_ldlib() -> str:
    return sysconfig.get_config_var('LDLIBRARY')


def get_python_lib_dir() -> str:
    if is_windows():
        return get_python_home()
    return sysconfig.get_config_var('LIBDIR')


def get_python_lib_link() -> list:
    if is_windows():
        return []
    lib_dir = get_python_lib_dir()
    return ['-Wl,-rpath,{0}'.format(lib_dir), '-L{0}'.format(lib_dir)]


def get_pylib_path() -> str:
    if is_windows():
        stdlib = get_python_lib_dir() + '\\' + get_pylib() + '.dll'
    elif is_osx():
        stdlib = get_python_lib_dir() + '/lib' + get_pylib() + '.dylib'
    elif is_linux():
        stdlib = get_python_lib_dir() + '/lib' + get_pylib() + '.so'
    else:
        raise ConfigError('Unknown platform.')
    return stdlib


def get_python_home() -> str:
    return sysconfig.get_config_var('prefix')


def get_numpy_include() -> str:
    import numpy
    inc_numpy = os.path.join(numpy.__path__[0], 'core', 'include')
    if os.path.exists(inc_numpy):
        return inc_numpy
    else:
        raise ConfigError("numpy include package not found in: {0}".format(inc_numpy))


def get_java_includes() -> list:
    java_home = os.getenv('JAVA_HOME')
    java_include = os.path.join(java_home, 'include')
    if is_osx():
        java_include_os = os.path.join(java_include, 'darwin')
    elif is_linux():
        java_include_os = os.path.join(java_include, 'linux')
    elif is_windows():
        java_include_os = os.path.join(java_include, 'win32')
    else:
        raise ConfigError('Unknown platform')
    if not os.path.exists(java_include) or not os.path.exists(java_include_os) or not os.path.exists(
            os.path.join(java_include, 'jni.h')):
        raise ConfigError("Include folder {0} doesn't exist.\n"
                          "Please check you have installed the JDK and JAVA_HOME.".format(java_include))
    return [java_include, java_include_os]
