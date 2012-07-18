
require '../rover.rb'

describe Mars::Field do

  it "validates field size on creation" do
    field = Mars::Field.new(-1,  0)
    field.max_x.should == 1
    field.max_y.should == 1
    field = Mars::Field.new( 0, -1)
    field.max_x.should == 1
    field.max_y.should == 1
    field = Mars::Field.new(-1, -1)
    field.max_x.should == 1
    field.max_y.should == 1
    field = Mars::Field.new( 0,  0)
    field.max_x.should == 1
    field.max_y.should == 1
  end

  it "sets the field size correctly" do
    field = Mars::Field.new(10, 9)
    field.max_x == 10
    field.max_y == 9
  end

  it "validates rover deployment positions" do
    field = Mars::Field.new(7,8)
    # check that improperly deployed rovers are not added to the field
    field.deploy_rover( 0, -1, "N")
    field.deploy_rover(-1,  0, "S")
    field.deploy_rover(-1, -1, "E")
    field.deploy_rover( 8,  7, "W")
    field.deploy_rover( 7,  9, "N")
    field.deploy_rover( 8,  9, "S")
    field.deploy_rover( 0,  9, "E")
    field.deploy_rover( 8,  0, "w")
    invalid_headings = ('A'..'Z').to_a - ["N", "S", "E", "W"]
    invalid_headings.each do |heading|
      field.deploy_rover( 1, 1, heading)
    end
    field.rovers.should be_empty

    # check that valid deployments are ok
    field.deploy_rover( 1,  2, "N")
    field.deploy_rover( 7,  8, "S")
    field.deploy_rover( 0,  0, "E")
    field.deploy_rover( 4,  5, "W")
    field.deploy_rover( 1,  2, "n")
    field.deploy_rover( 7,  8, "s")
    field.deploy_rover( 0,  0, "e")
    field.deploy_rover( 4,  5, "w")

    field.rovers.length.should == 8
    
end
  
  it "can have deployed one or more rovers" do
    field = Mars::Field.new(10, 10)
    # deploy several rovers to easy different coords
    # (1,1), (2,2), ...
    (1..3).each do |rover_num|
      field.deploy_rover(rover_num, rover_num, "N")
    end
    # check the list of rovers
    i = 0
    field.rovers.each do |rover|
      i += 1
      rover.pos_x.should == i
      rover.pos_y.should == i
    end
  end
end

describe Mars::Rover do

  it "should be able to turn left" do
    # make sure the heading adjusts correctly with left turn inputs
    rover = Mars::Rover.new(1, 1, "N")
    rover.command("L")
    rover.heading.should == "W"
    rover.command("L")
    rover.heading.should == "S"
    rover.command("L")
    rover.heading.should == "E"
    rover.command("L")
    rover.heading.should == "N"

    # verify multiple turns at once
    rover.command("LL")
    rover.heading.should == "S"
    rover.command("LLL")
    rover.heading.should == "W"
    rover.command("LLLL")
    rover.heading.should == "W"
  end

  it "should be able to turn right" do
    # verify heading adjusts correctly when turning right
    rover = Mars::Rover.new(1, 1, "N")
    rover.command("R")
    rover.heading.should == "E"
    rover.command("R")
    rover.heading.should == "S"
    rover.command("R")
    rover.heading.should == "W"
    rover.command("R")
    rover.heading.should == "N"

    # verify multiple turns at once
    rover.command("RR")
    rover.heading.should == "S"
    rover.command("RRR")
    rover.heading.should == "E"
    rover.command("RRRR")
    rover.heading.should == "E"

  end

  it "should move forward in the correct direction" do
    rover = Mars::Rover.new(5, 5, "N")
    rover.command("M")
    rover.pos_x.should == 5
    rover.pos_y.should == 6

    rover.command("L")
    rover.command("M")
    rover.pos_x.should == 4
    rover.pos_y.should == 6

    rover.command("L")
    rover.command("MM")
    rover.pos_x.should == 4
    rover.pos_y.should == 4

    rover.command("L")
    rover.command("MMM")
    rover.pos_x.should == 7
    rover.pos_y.should == 4
  end

  it "should handle the test input cases correctly" do
    # Test Input:
    # 5 5
    # 1 2 N
    # LMLMLMLMM
    # 3 3 E
    # MMRMMRMRRM
    # 
    # Expected Output:
    # 1 3 N
    # 5 1 E
    f = Mars::Field.new(5,5)

    f.deploy_rover(1, 2, "N")

    r1 = f.rovers[0]
    r1.command("LMLMLMLMM")
    r1.pos_x.should   == 1
    r1.pos_y.should   == 3
    r1.heading.should == "N"

    f.deploy_rover(3, 3, "E")
    r2 = f.rovers[1]
    r2.command("MMRMMRMRRM")
    r2.pos_x.should   == 5
    r2.pos_y.should   == 1
    r2.heading.should == "E"
    
  end
end
