# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.20

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

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /app/extra/clion/bin/cmake/linux/bin/cmake

# The command to remove a file.
RM = /app/extra/clion/bin/cmake/linux/bin/cmake -E rm -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /home/ianmoone/Desktop/POO/P/f3_ex1

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /home/ianmoone/Desktop/POO/P/f3_ex1/cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/f3_ex1.dir/depend.make
# Include the progress variables for this target.
include CMakeFiles/f3_ex1.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/f3_ex1.dir/flags.make

CMakeFiles/f3_ex1.dir/main.cpp.o: CMakeFiles/f3_ex1.dir/flags.make
CMakeFiles/f3_ex1.dir/main.cpp.o: ../main.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/ianmoone/Desktop/POO/P/f3_ex1/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/f3_ex1.dir/main.cpp.o"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/f3_ex1.dir/main.cpp.o -c /home/ianmoone/Desktop/POO/P/f3_ex1/main.cpp

CMakeFiles/f3_ex1.dir/main.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/f3_ex1.dir/main.cpp.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/ianmoone/Desktop/POO/P/f3_ex1/main.cpp > CMakeFiles/f3_ex1.dir/main.cpp.i

CMakeFiles/f3_ex1.dir/main.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/f3_ex1.dir/main.cpp.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/ianmoone/Desktop/POO/P/f3_ex1/main.cpp -o CMakeFiles/f3_ex1.dir/main.cpp.s

CMakeFiles/f3_ex1.dir/ponto.cpp.o: CMakeFiles/f3_ex1.dir/flags.make
CMakeFiles/f3_ex1.dir/ponto.cpp.o: ../ponto.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/ianmoone/Desktop/POO/P/f3_ex1/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Building CXX object CMakeFiles/f3_ex1.dir/ponto.cpp.o"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/f3_ex1.dir/ponto.cpp.o -c /home/ianmoone/Desktop/POO/P/f3_ex1/ponto.cpp

CMakeFiles/f3_ex1.dir/ponto.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/f3_ex1.dir/ponto.cpp.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/ianmoone/Desktop/POO/P/f3_ex1/ponto.cpp > CMakeFiles/f3_ex1.dir/ponto.cpp.i

CMakeFiles/f3_ex1.dir/ponto.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/f3_ex1.dir/ponto.cpp.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/ianmoone/Desktop/POO/P/f3_ex1/ponto.cpp -o CMakeFiles/f3_ex1.dir/ponto.cpp.s

# Object files for target f3_ex1
f3_ex1_OBJECTS = \
"CMakeFiles/f3_ex1.dir/main.cpp.o" \
"CMakeFiles/f3_ex1.dir/ponto.cpp.o"

# External object files for target f3_ex1
f3_ex1_EXTERNAL_OBJECTS =

f3_ex1: CMakeFiles/f3_ex1.dir/main.cpp.o
f3_ex1: CMakeFiles/f3_ex1.dir/ponto.cpp.o
f3_ex1: CMakeFiles/f3_ex1.dir/build.make
f3_ex1: CMakeFiles/f3_ex1.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/home/ianmoone/Desktop/POO/P/f3_ex1/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_3) "Linking CXX executable f3_ex1"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/f3_ex1.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/f3_ex1.dir/build: f3_ex1
.PHONY : CMakeFiles/f3_ex1.dir/build

CMakeFiles/f3_ex1.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/f3_ex1.dir/cmake_clean.cmake
.PHONY : CMakeFiles/f3_ex1.dir/clean

CMakeFiles/f3_ex1.dir/depend:
	cd /home/ianmoone/Desktop/POO/P/f3_ex1/cmake-build-debug && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /home/ianmoone/Desktop/POO/P/f3_ex1 /home/ianmoone/Desktop/POO/P/f3_ex1 /home/ianmoone/Desktop/POO/P/f3_ex1/cmake-build-debug /home/ianmoone/Desktop/POO/P/f3_ex1/cmake-build-debug /home/ianmoone/Desktop/POO/P/f3_ex1/cmake-build-debug/CMakeFiles/f3_ex1.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/f3_ex1.dir/depend
