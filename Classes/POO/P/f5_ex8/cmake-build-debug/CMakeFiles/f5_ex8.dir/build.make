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
CMAKE_COMMAND = "C:\Program Files\JetBrains\CLion 2021.2.3\bin\cmake\win\bin\cmake.exe"

# The command to remove a file.
RM = "C:\Program Files\JetBrains\CLion 2021.2.3\bin\cmake\win\bin\cmake.exe" -E rm -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = C:\Users\ianmoone\Desktop\POO\P\f5_ex8

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = C:\Users\ianmoone\Desktop\POO\P\f5_ex8\cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/f5_ex8.dir/depend.make
# Include the progress variables for this target.
include CMakeFiles/f5_ex8.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/f5_ex8.dir/flags.make

CMakeFiles/f5_ex8.dir/main.cpp.obj: CMakeFiles/f5_ex8.dir/flags.make
CMakeFiles/f5_ex8.dir/main.cpp.obj: ../main.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=C:\Users\ianmoone\Desktop\POO\P\f5_ex8\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/f5_ex8.dir/main.cpp.obj"
	C:\MinGW\bin\g++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles\f5_ex8.dir\main.cpp.obj -c C:\Users\ianmoone\Desktop\POO\P\f5_ex8\main.cpp

CMakeFiles/f5_ex8.dir/main.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/f5_ex8.dir/main.cpp.i"
	C:\MinGW\bin\g++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E C:\Users\ianmoone\Desktop\POO\P\f5_ex8\main.cpp > CMakeFiles\f5_ex8.dir\main.cpp.i

CMakeFiles/f5_ex8.dir/main.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/f5_ex8.dir/main.cpp.s"
	C:\MinGW\bin\g++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S C:\Users\ianmoone\Desktop\POO\P\f5_ex8\main.cpp -o CMakeFiles\f5_ex8.dir\main.cpp.s

# Object files for target f5_ex8
f5_ex8_OBJECTS = \
"CMakeFiles/f5_ex8.dir/main.cpp.obj"

# External object files for target f5_ex8
f5_ex8_EXTERNAL_OBJECTS =

f5_ex8.exe: CMakeFiles/f5_ex8.dir/main.cpp.obj
f5_ex8.exe: CMakeFiles/f5_ex8.dir/build.make
f5_ex8.exe: CMakeFiles/f5_ex8.dir/linklibs.rsp
f5_ex8.exe: CMakeFiles/f5_ex8.dir/objects1.rsp
f5_ex8.exe: CMakeFiles/f5_ex8.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=C:\Users\ianmoone\Desktop\POO\P\f5_ex8\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable f5_ex8.exe"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles\f5_ex8.dir\link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/f5_ex8.dir/build: f5_ex8.exe
.PHONY : CMakeFiles/f5_ex8.dir/build

CMakeFiles/f5_ex8.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles\f5_ex8.dir\cmake_clean.cmake
.PHONY : CMakeFiles/f5_ex8.dir/clean

CMakeFiles/f5_ex8.dir/depend:
	$(CMAKE_COMMAND) -E cmake_depends "MinGW Makefiles" C:\Users\ianmoone\Desktop\POO\P\f5_ex8 C:\Users\ianmoone\Desktop\POO\P\f5_ex8 C:\Users\ianmoone\Desktop\POO\P\f5_ex8\cmake-build-debug C:\Users\ianmoone\Desktop\POO\P\f5_ex8\cmake-build-debug C:\Users\ianmoone\Desktop\POO\P\f5_ex8\cmake-build-debug\CMakeFiles\f5_ex8.dir\DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/f5_ex8.dir/depend

