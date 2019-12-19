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
    return sysconfig.get_config_var('LIBDIR') + '/' + get_ldlib()


def get_python_home():
    return sysconfig.get_config_var('prefix')
