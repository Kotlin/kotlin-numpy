import os.path
from distutils.command.build_ext import build_ext

from buildScr.python.utils import is_windows, is_osx, is_linux


class build_ktlib(build_ext):
    def finalize_options(self):
        use_default_build_lib = self.build_lib is None
        build_ext.finalize_options(self)
        if use_default_build_lib:
            self.build_lib = os.path.join(self.build_lib, "ktnumpy")

    # workaround https://bugs.python.org/issue35893
    def export_none(self, ext):
        pass

    def run(self):
        import re

        if is_windows():
            build_ext.get_export_symbols = self.export_none

        build_ext.run(self)

        ktlib = self.get_outputs()[0]
        if is_windows():
            ktnumpy = re.sub(r'ktnumpy\..+', 'ktnumpy.dll', ktlib)
        elif is_osx():
            ktnumpy = re.sub(r'ktnumpy\..+', 'libktnumpy.dylib', ktlib)
        elif is_linux():
            ktnumpy = re.sub(r'ktnumpy\..+', 'libktnumpy.so', ktlib)
        else:
            raise RuntimeError('Unknown platform.')
        self.copy_file(ktlib, ktnumpy)
