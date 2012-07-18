#!/usr/bin/env ruby
# -*- coding: utf-8 -*-

##
##
## Mission to Mars
##
## pass this file the name of a text file with input, e.g.
## $ ./mission_to_mars.rb input.txt
##
## input file format:
## The first line of input is the upper-right coordinates of the plateau, the
## lower-left coordinates are assumed to be 0,0.
##
## The rest of the input is information pertaining to the rovers that have
## been deployed. Each rover has two lines of input. The first line gives the
## rover’s position, and the second line is a series of instructions telling
## the rover how to explore the plateau.
##
## The position is made up of two integers and a letter separated by spaces,
## corresponding to the x and y co-ordinates and the rover’s orientation.
##
## e.g.
## 5 5
## 1 2 N
## LMLMLMLMM
## 3 3 E
## MMRMMRMRRM

require 'rover.rb'

ARGV.each do|arg|
  puts "Using mission parameters from file: #{arg}"

  # open input file and read out the lines
  f = File.open(arg)
  lines = f.readlines
  f.close
  
  # make the field from the first line of input
  field_size = lines[0].split(" ")
  field = Mars::Field.new(field_size[0].to_i, field_size[1].to_i)

  # Read lines in pairs. First is rover reployment position. Next
  # is rover commands
  i = 1
  lines.length/2.times do
    #first line is where the rover starts from
    landing_site = lines[i].split(" ")
    field.deploy_rover(landing_site[0].to_i, landing_site[1].to_i, landing_site[2])
    i += 1
    # next line is the command list
    field.rovers[-1].command(lines[i].chomp)
    field.rovers[-1].to_s
    i += 1
  end
  
  
end

