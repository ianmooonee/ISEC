# CMAKE generated file: DO NOT EDIT!
# Generated by "MinGW Makefiles" Generator, CMake Version 3.20

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:

#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:

# Disable VCS-based implicit rules.
% : %,v

# Disable VCS-based implicit rules.
% : RCS/%

# Disable VCS-based implicit rules.
% : RCS/%,v

# Disable VCS-based implicit rules.
% : SCCS/s.%

# Disable VCS-based implicit rules.
% : s.%

.SUFFIXES: .hpux_make_needs_suffix_list

# Command-line flag to silence nested $(MAKE).
$(VERBOSE)MAKESILENT = -s

#Suppress display of executed commands.
$(VERBOSE).SILENT:

# A target that is always out of date.
cmake_force:
.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

SHELL = cmd.exe

# The CMake executable.
CMAKE_COMMAND = C:\Users\ianmoone\AppData\Local\JetBrains\Toolbox\apps\CLion\ch-0\212.5457.51\bin\cmake\win\bin\cmake.exe

# The command to remove a file.
RM = C:\Users\ianmoone\AppData\Local\JetBrains\Toolbox\apps\CLion\ch-0\212.5457.51\bin\cmake\win\bin\cmake.exe -E rm -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = C:\Users\ianmoone\Desktop\Isec21.22\aulas\POO\Exames\exame_1920_normal

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = C:\Users\ianmoone\Desktop\Isec21.22\aulas\POO\Exames\exame_1920_normal\cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/exame_1920_normal.dir/depend.make
# Include the progress variables for this target.
include CMakeFiles/exame_1920_normal.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/exame_1920_normal.dir/flags.make

CMakeFiles/exame_1920_normal.dir/main.cpp.obj: CMakeFiles/exame_1920_normal.dir/flags.make
CMakeFiles/exame_1920_normal.dir/main.cpp.obj: ../main.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=C:\Users\ianmoone\Desktop\Isec21.22\aulas\POO\Exames\exame_1920_normal\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/exame_1920_normal.dir/main.cpp.obj"
	C:\MinGW\bin\g++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles\exame_1920_normal.dir\main.cpp.obj -c C:\Users\ianmoone\Desktop\Isec21.22\aulas\POO\Exames\exame_1920_normal\main.cpp

CMakeFiles/exame_1920_normal.dir/main.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/exame_1920_normal.dir/main.cpp.i"
	C:\MinGW\bin\g++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E C:\Users\ianmoone\Desktop\Isec21.22\aulas\POO\Exames\exame_1920_normal\main.cpp > CMakeFiles\exame_1920_normal.dir\main.cpp.i

CMakeFiles/exame_1920_normal.dir/main.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/exame_1920_normal.dir/main.cpp.s"
	C:\MinGW\bin\g++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S C:\Users\ianmoone\Desktop\Isec21.22\aulas\POO\Exames\exame_1920_normal\main.cpp -o CMakeFiles\exame_1920_normal.dir\main.cpp.s

# Object files for target exame_1920_normal
exame_1920_normal_OBJECTS = \
"CMakeFiles/exame_1920_normal.dir/main.cpp.obj"

# External object files for target exame_1920_normal
exame_1920_normal_EXTERNAL_OBJECTS =

exame_1920_normal.exe: CMakeFiles/exame_1920_normal.dir/main.cpp.obj
exame_1920_normal.exe: CMakeFiles/exame_1920_normal.dir/build.make
exame_1920_normal.exe: CMakeFiles/exame_1920_normal.dir/linklibs.rsp
exame_1920_normal.exe: CMakeFiles/exame_1920_normal.dir/objects1.rsp
exame_1920_normal.exe: CMakeFiles/exame_1920_normal.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=C:\Users\ianmoone\Desktop\Isec21.22\aulas\POO\Exames\exame_1920_normal\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable exame_1920_normal.exe"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles\exame_1920_normal.dir\link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/exame_1920_normal.dir/build: exame_1920_normal.exe
.PHONY : CMakeFiles/exame_1920_normal.dir/build

CMakeFiles/exame_1920_normal.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles\exame_1920_normal.dir\cmake_clean.cmake
.PHONY : CMakeFiles/exame_1920_normal.dir/clean

CMakeFiles/exame_1920_normal.dir/depend:
	$(CMAKE_COMMAND) -E cmake_depends "MinGW Makefiles" C:\Users\ianmoone\Desktop\Isec21.22\aulas\POO\Exames\exame_1920_normal C:\Users\ianmoone\Desktop\Isec21.22\aulas\POO\Exames\exame_1920_normal C:\Users\ianmoone\Desktop\Isec21.22\aulas\POO\Exames\exame_1920_normal\cmake-build-debug C:\Users\ianmoone\Desktop\Isec21.22\aulas\POO\Exames\exame_1920_normal\cmake-build-debug C:\Users\ianmoone\Desktop\Isec21.22\aulas\POO\Exames\exame_1920_normal\cmake-build-debug\CMakeFiles\exame_1920_normal.dir\DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/exame_1920_normal.dir/depend
