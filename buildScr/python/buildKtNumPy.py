import subprocess
import sysconfig
from distutils import sysconfig as sysc


def get_os():
    if 'win' in sysconfig.get_platform():
        return 'win'
    if 'macosx' in sysconfig.get_platform():
        return 'macosx'
    if 'linux' in sysconfig.get_platform():
        return 'linux'


def get_py_lib():
    version = sysc.get_config_var('VERSION')
    ldversion = sysc.get_config_var('LDVERSION')
    if ldversion:
        version = ldversion
    pylib = 'python' + version
    return pylib


if __name__ == '__main__':
    version = '0.1.0'

    cur_os = get_os()  # current os

    cmd = [
        sysc.get_config_var('CC'),
        '-shared',
        '*.o'
    ]

    lpylib = get_py_lib()
    lpydir = sysc.get_config_var('LIBDIR')
    dest = ""
    print(lpylib)
    print(lpydir)
    if lpylib and lpydir:
        if cur_os == 'macosx':
            dest = "/usr/local/lib/libktnumpy.dylib"
        if cur_os == 'linux':
            dest = "/usr/local/lib/libktnumpy.so"
        if cur_os == 'win':
            cmd.append('-DWIN')
            dest = "C:\\System32\\libktnumpy.dll"
        cmd.append('-Wl,-rpath,{0}'.format(lpydir))
        cmd.append('-L{0}'.format(lpydir))
        cmd.append('-l{0}'.format(lpylib))

    cmd.append('-o')
    cmd.append(dest)

    subprocess.run(" ".join(cmd), shell=True, check=True, text=True)
