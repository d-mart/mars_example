
module Mars

  # part of planet to be explored.
  # Field is a rectangle with coordinates (0,0) to (x,y)
  class Field
    attr_reader :max_x, :max_y
    attr :rover_list

    # make new field.
    # both x and y must be at least 0 and
    # one must be greater than 0
    def initialize(x, y)
      # initialize list of rovers
      @rover_list = Array.new
      begin
        # set field
        raise ArgumentError.new("Dimensions cannot be negative") if x < 0 || y < 0
        raise ArgumentError.new("At least one dimension must be > 0") if not x > 0 || y > 0
        @max_x = x
        @max_y = y
      rescue ArgumentError => e
        puts e.message
        puts "A field with (1, 1) dimensions was created"
        @max_x = 1
        @max_y = 1
      end
    end

    # add a rover to the field if the initial
    # position is in the field and the heading
    # starts with a valid char
    def deploy_rover(pos_x, pos_y, heading)
      if "NSEW".include?(heading[0..0].upcase)
        if in_field?(pos_x, pos_y)
          @rover_list.push(Rover.new(pos_x, pos_y, heading))
        end
      end
    end

    # Check if specified coord lies within the field
    def in_field?(x, y)
      x.between?(0, max_x) && y.between?(0, max_y)
    end

    # get list of rovers deployed to field.
    def rovers
      @rover_list
    end

  end

  class Rover
    attr_reader :heading
    attr_reader :pos_x, :pos_y

    def initialize(pos_x, pos_y, heading)
      @pos_x = pos_x
      @pos_y = pos_y
      @heading = heading
    end

    # Accepts arbitrary length of commands as a string
    # valid commands are
    #   L - rotate -90 degrees
    #   R - rotate  90 degrees
    #   M - move one unit in current heading
    # other chars in the command list are ignored
    def command(command_list)
      command_list.upcase.split("").each do |command|
        case command
        when "L"
          rotate_left
        when "R"
          rotate_right
        when "M"
          move_forward
        else
          puts "Ignored command: #{command}"
        end
      end
    end

    # rotate -90 degrees
    # if invalid input, just return current heading
    def rotate_left
      heading_list = "NESW"
      index = heading_list.index(@heading)
      if index == 0
        @heading = heading_list[3..3] # ruby 1.8.7 safe for returning string instead of fixnum
      else
        index -= 1
        @heading = heading_list[index..index]
      end
    end

    # rotate 90 degrees
    # if invalid input, just return current heading
    def rotate_right
      heading_list = "NESW"
      index = heading_list.index(@heading)
      if index == 3
        @heading = heading_list[0..0] # ruby 1.8.7 safe for returning string instead of fixnum
      else
        index += 1
        @heading = heading_list[index..index]
      end
    end

    # move forward one map unit in the current heading
    def move_forward
      case heading.upcase
      when "N"
        @pos_y += 1
      when "S"
        @pos_y -= 1
      when "E"
        @pos_x += 1
      when "W"
        @pos_x -= 1
      end
    end

    def to_s
      puts "#{@pos_x.to_s} #{@pos_y.to_s} #{@heading}"
    end
  end # class Rover
end # module Mars
