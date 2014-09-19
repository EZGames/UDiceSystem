# CodeSkulptor runs Python programs in your browser.
# Click the upper left button to run this simple demo.

# CodeSkulptor runs in Chrome 18+, Firefox 11+, and Safari 6+.
# Some features may work in other browsers, but do not expect
# full functionality.  It does NOT run in Internet Explorer.

import simplegui
import random
import math

#towers: ['type', pos, [cur_charge, max_charge], damage, range, [cur_health, max_health]]
#fire: slow charge, med damage, hits everything in range(150)
#water: med charge, med damage, damages armour(100)
#air: very fast charge, low damage, cheap (50)
#earth: med charge, med damage, no immunity, expensive(200)

#monster: [to_path, [progress, inv_speed], [cur_health, max_health], [cur_armour, max_armour], [r_colour, g_colour, b_colour]]
#to_path is the index of the square in path[] the monster is moving towards
#progress is how many steps along from to_path-1 to to_path the monster is
#inv_speed indicates how many steps it takes the monster to get to to_path
#cur_health is health of the monster, reduces as it gets hit, dies at zero
#max_health maximum, ansd starting health
#cur_armour is the current armour value of the monster, reduces damage by this amount
#max_armour initial starting armour
#r/g/b_colour - the colour the monnster is drawn, also immunity level for fire/air/water

# path is a sequence of [x, y], where the next item changes x or y by exactly +1 or -1
path = []
path_walls = []
path_tiles = random.randrange(4)

# beams display the tower attacks
beams = []

# state keeps track of game state
#0 - Start game menu
#1 - Playing
#2 - Game over
state = 0

active_monsters = []
pending_monsters = []

base_towers = []
base_towers.append(['fire', [0,0], [0, 250], [25, 50], 75, [50, 50], 1, 150])
base_towers.append(['water', [0,0], [0, 50], [20, 30], 75, [70, 70], 1, 100])
base_towers.append(['air', [0,0], [0, 30], [10, 20], 100, [30, 30], 1, 50])
base_towers.append(['earth', [0,0], [0, 110], [20, 50], 75, [150, 150], 1, 200])

# set up buttons for each state
buttons = []
buttons1 = []
buttons1.append([25, 75, 175, 150, 'Long', [0, 0, 255]])
buttons1.append([25, 175, 175, 250, 'Medium', [0, 0, 255]])
buttons1.append([25, 275, 175, 350, 'Short', [0, 0, 255]])
buttons1.append([225, 75, 375, 150, 'Lots', [255, 255, 0]])
buttons1.append([225, 175, 375, 250, 'Average', [255, 255, 0]])
buttons1.append([225, 275, 375, 350, 'Poor', [255, 255, 0]])
buttons1.append([425, 75, 575, 150, 'Low', [0, 255, 0]])
buttons1.append([425, 175, 575, 250, 'Medium', [0, 255, 0]])
buttons1.append([425, 275, 575, 350, 'High', [0, 255, 0]])
buttons1.append([625, 500, 775, 575, 'Start', [128, 128, 128]])
buttons1.append([25, 375, 175, 425, 'Auto Repair', [128, 128, 128]])
buttons1.append([25, 450, 175, 500, 'Monsters Loop', [128, 128, 128]])
buttons.append(buttons1)
buttons2 = []
buttons2.append([25, 25, 100, 100, '', [255, 0, 0]]) # fire
buttons2.append([125, 25, 200, 100, '', [0, 0, 255]]) # water
buttons2.append([225, 25, 300, 100, '', [0, 255, 0]]) # air
buttons2.append([325, 25, 400, 100, '', [255, 255, 0]]) # earth
buttons2.append([425, 25, 500, 100, '', [255, 0, 255]]) # up grade
buttons2.append([525, 25, 600, 100, '', [0, 255, 255]]) # repair
buttons2.append([625, 25, 700, 100, '', [255, 255, 255]]) # next wave
buttons2.append([725, 25, 760, 60, '', [128, 128, 128]]) # range
buttons2.append([765, 25, 800, 60, '', [128, 128, 128]]) # dmg
buttons2.append([725, 65, 760, 100, '', [128, 128, 128]]) # charge
buttons2.append([765, 65, 800, 100, '', [128, 128, 128]]) # pause
buttons.append(buttons2)
buttons3 = []
buttons3.append([625, 500, 775, 575, 'New Game', [128, 128, 128]])
buttons.append(buttons3)

# Helper functions:
def generate_path(start):
    # set up dictionary and list for use in making the path
    corner_dict = {-1: 0, 5: 0, 0: 1, 1: 1, 2: 2, 4: 2, 3: 3, 6: 3}
    corner_mod =[[-1,-1], [0, -1], [0,0], [-1, 0]]

    
    # randomly generates a path
    # it iteratively extends the path from an initial seed
    # start indicates initial conditions and chances for different extentions
    # 0 - short start path, 66% chance of short option
    # 1 - medium start path, 50% chance of short option
    # 2 - long start path, 33% chance of short option
    
    # path[0] is the start square, and is just off screen
    #    monsters start here, and are moving towards path[1]
    # path[len(path) -1] is the end square and is also just off screen
    #    monsters explode when they start to move towards this square.
    global path
    global path_walls
    path = []
    path_walls = []
    
    # set up the general shape of the path, and how twisty it is
    if start == 0:
        path = [[0,1], [1,1], [2,1], [3,1]]
        iterations = 3
        long_path_chance = [True, False, False]
    elif start == 1:
        path = [[1,2], [1,1], [2,1], [2,2]]
        iterations = 3
        long_path_chance = [True, False]        
    elif start == 2:
        path = [[0,1], [1,1], [2,1], [3,1]]
        if random.choice([True, True, False]):
            path.extend([[4,1], [4,2]])
        path.extend([[3,2], [2,2]])
        if random.choice([True, True, False]):
            path.append([2,3])
        else:
            path.extend([[1,2], [1,3]])
        iterations = 2
        long_path_chance = [True, True, False]

    # this section takes the initial path, and expands it
    # by sub-dividing each block into 4 new blocks where the path could go
    #
    # each block has an 'in' side, where the path before it is
    # each block has an 'out' side, where the next path is
    # in each iteration each block is sub divided into a 2 x 2 matrix,
    # +-----+       +--+--+
    # |     |out    |  |x | (-1)
    # |     =>   => +--+--+
    # |  A  |       | x| x| ( 0)
    # +--|--+       +--+--+
    #    in         (-1)(0)
    # and each in/out edge is divided in half, and one half is choosen
    # for the path to go through
    # from there, 1 to 4 new, smaller blocks are added to the new path
   
    for iter in range(iterations):
        out_edge_side = random.choice([-1, 0])
        # get the common edge between path[0] and path[1]
        # based on how x and y change
        # +--0--+
        # |     |
        # 3     1
        # |     |
        # +--2--+
        
        # path[0] is replaced with a single new path[0]
        x, y = path[0]
        x_next, y_next = path[1]
        if y - y_next == 1:
            out_edge = 0
            new_path = [[2 * x + out_edge_side, 2* y - 1]]
        elif y - y_next == -1:
            out_edge = 2
            new_path = [[2 * x + out_edge_side, 2 * y]]
        elif x - x_next == -1:
            out_edge = 1
            new_path = [[2 * x, 2 * y + out_edge_side]]
        elif x - x_next == 1:
            out_edge = 3
            new_path = [[2 * x - 1, 2 * y + out_edge_side]]

        # the in_edge and in_edge_side are based on the out of the previous path
        in_edge = (out_edge + 2) % 4
        in_edge_side = out_edge_side
        
        
        # the four new blocks at [x,y] are
        # [2x-1, 2y-1] [2x, 2y-1]
        # [2x-1, 2y]     [2x, 2y]
        path_block = 1
        while path_block < len(path) - 1:
            # randomly choose which side of the out edge to use
            # in_edge_side came from previous iteration
            out_edge_side = random.choice([-1, 0])
            
            # get new out_edge
            # in_edge comes from previous iteration
            x, y = path[path_block]
            x_next, y_next = path[path_block + 1]
            if y - y_next == 1:
                out_edge = 0
            elif y - y_next == -1:
                out_edge = 2
            elif x - x_next == -1:
                out_edge = 1
            elif x - x_next == 1:
                out_edge = 3
                
            # based on in_edge, out_edge, in_edge_side, out_edge_side
            # add appropriate blocks to new_path
            # there are 48 possibilities, with usually 2 possible routes
            # pick long route based on long_path_chance
            # it may be possible to simplify, since most are rotations of others
            
            # go by in corner and out corner
            # +-+-+
            # |0|1|
            # +-+-+
            # |3|2|
            # +-+-+
            in_corner = corner_dict[in_edge * 2 + in_edge_side]
            out_corner = corner_dict[out_edge * 2 + out_edge_side]
            
            # the in_corner is always added
            new_path.append([2*x + corner_mod[in_corner][0], 2*y + corner_mod[in_corner][1]])
            if out_corner == in_corner:
                # the path goes in and out the same corner, so just the one already added is needed
                # this section is included because the path could loop around as a bridge
                nul = 0
            elif out_corner == (in_corner + 1) % 4:
                # the path goes in one corner, and then comes out the next one to the right
                # the long path goes counter-clockwise, while the short path just goes to out_corner
                if random.choice(long_path_chance):
                    cornerA = (in_corner + 3) % 4
                    cornerB = (in_corner + 2) % 4
                    new_path.append([2*x + corner_mod[cornerA][0], 2*y + corner_mod[cornerA][1]])
                    new_path.append([2*x + corner_mod[cornerB][0], 2*y + corner_mod[cornerB][1]])
                # always add out corner
                new_path.append([2*x + corner_mod[out_corner][0], 2*y + corner_mod[out_corner][1]])
            elif out_corner == (in_corner + 2) % 4:
                # the path goes across the block, either left or right, equally
                if random.choice([True, False]):
                    cornerA = (in_corner + 3) % 4
                else:
                    cornerA = (in_corner + 1) % 4
                new_path.append([2*x + corner_mod[cornerA][0], 2*y + corner_mod[cornerA][1]])
                # always add out corner
                new_path.append([2*x + corner_mod[out_corner][0], 2*y + corner_mod[out_corner][1]])
            elif out_corner == (in_corner + 3) % 4:
                # the path goes in one corner, and then comes out the next one to the left
                # the long path goes clockwise, while the short path just goes to out_corner
                if random.choice(long_path_chance):
                    cornerA = (in_corner + 1) % 4
                    cornerB = (in_corner + 2) % 4
                    new_path.append([2*x + corner_mod[cornerA][0], 2*y + corner_mod[cornerA][1]])
                    new_path.append([2*x + corner_mod[cornerB][0], 2*y + corner_mod[cornerB][1]])
                # always add out corner
                new_path.append([2*x + corner_mod[out_corner][0], 2*y + corner_mod[out_corner][1]])
                    

            
            # next iteration gets the out_edge info in in_edge data
            in_edge =(out_edge + 2) % 4
            in_edge_side = out_edge_side
            path_block += 1
            
        # finally, update the old end with a new end
        # based on last in_edge and in_edge_side
        
        x, y = path[-1]
        if in_edge == 0:
            new_path.append([2 * x + in_edge_side, 2* y - 1])
        elif in_edge == 2:
            new_path.append([2 * x + in_edge_side, 2 * y])
        elif in_edge == 1:
            new_path.append([2 * x, 2 * y + in_edge_side])
        elif in_edge == 3:
            new_path.append([2 * x - 1, 2 * y + in_edge_side])
            
        # update path with new path
        path = []
        path = new_path
        
    # use path to generate list of walls to draw
    x, y = path[0]
    x_next, y_next = path[1]
    if y - y_next == 1:
        out_edge = 0
    elif y - y_next == -1:
        out_edge = 2
    elif x - x_next == -1:
        out_edge = 1
    elif x - x_next == 1:
        out_edge = 3

    path_walls.append([0,1,2,3])
    # the in_edge and in_edge_side are based on the out of the previous path
    in_edge = (out_edge + 2) % 4
    
    for path_block in range(1, len(path)-1):
        x, y = path[path_block]
        x_next, y_next = path[path_block + 1]
        if y - y_next == 1:
            out_edge = 0
        elif y - y_next == -1:
            out_edge = 2
        elif x - x_next == -1:
            out_edge = 1
        elif x - x_next == 1:
            out_edge = 3
        walls =[0,1,2,3]
        walls.remove(in_edge)
        walls.remove(out_edge)
        path_walls.append(walls)
        in_edge = (out_edge + 2) % 4
    path_walls.append([0,1,2,3])

def new_wave():
    global pending_monsters
    global wave
    
    wave += 1
    num_monsters = 2
    for i in range(wave):
        if random.randrange(5) == 0:
            num_monsters = num_monsters + 1
    # monsters always start at 0, moving towards 1
    towards = 1
    # progress starts high and decreases as wave increases
    max_prog= max(int(50 - wave/3),20)
    min_prog = max(int(30 - wave/2), 10)
    first_prog = random.randrange(min_prog, max_prog)
    second_prog = random.randrange(min_prog, max_prog)
    wave_max_prog = max(first_prog, second_prog)
    wave_min_prog = min(first_prog, second_prog)
    wave_step_prog = (wave_max_prog - wave_min_prog)/(num_monsters - 1) + 3
    # health increases continually as waves increase
    min_health = wave * int(math.sqrt(wave)) + 5
    max_health = wave * wave * 5 + 15
    # armour increases slowly, but steadally as wave increases
    min_armour = 1
    max_armour = wave * 2
    # immunity range is constant
    colour = rnd_col()
    for i in range(num_monsters):
        prog = [0, int(wave_min_prog + wave_step_prog * i)]
        mon_health = random.randrange(min_health, max_health)
        health = [mon_health, mon_health]
        mon_armour = random.randrange(min_armour, max_armour)
        armour = [mon_armour, mon_armour]
        pending_monsters.append([towards, prog, health, armour, colour])
    if not paused:
        activate_timer.start()
    
def activate_monster():
    global active_monsters
    global pending_monsters
    
    active_monsters.append(pending_monsters.pop(0))
    if len(pending_monsters) == 0:
        activate_timer.stop()
    
def hex(code):
    hex_list=['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F']
    low = code % 16
    high = (code % 256) // 16
    return hex_list[high] + hex_list[low]

def rgb_to_hex(rgb, a=1):
    ''' ([int, int, int]) -> str

    Takes an RGB triple ([0..255, 0..255, 0..255]) and returns
    a hex color code string ('#000000' - '#FFFFFF')

    '''
    return 'rgba(' + str(rgb[0]) + ', ' + str(rgb[1]) + ', ' + str(rgb[2]) + ', ' + str(a) + ')'

def rnd_col():
    return [random.randrange(colour_min,colour_max), random.randrange(colour_min,colour_max), random.randrange(colour_min,colour_max)]

def update():
    global active_monsters
    global towers
    global beams
    global state
    global gold
    global score

    if len(active_monsters) == 0 and len(pending_monsters) == 0:
        wave_timer.stop()
        new_wave()
        wave_timer.start()
        
    new_beams = []
    for beam in beams:
        start, end, colour, alpha = beam
        alpha -= 0.05
        if alpha > 0.05:
            new_beams.append([start, end, colour, alpha])
    beams = new_beams
    
    new_towers=[]
    for tower in towers:
        typ, pos, charge, damage, rng, health, level = tower
        x_pos, y_pos = pos
        cur_health, max_health = health
        cur_charge, max_charge = charge
        min_damage, max_damage = damage
        if cur_health<=0:
            #tower destroyed
            nul = 0
        else:
            if auto_repair:
                if max_health - cur_health <= gold:
                    gold = gold - (max_health - cur_health)
                    health = [max_health, max_health]
            cur_charge +=1
            if cur_charge >= max_charge:
                cur_charge = max_charge
                monster_hit = False
                new_monsters=[]
                fire_hit = False
                for monster in active_monsters:
                    if monster_hit:
                        new_monsters.append(monster)
                    else:
                        x = x_pos * 50 - 25
                        y = y_pos * 50 + 175

                        m_toward, m_prog, m_health, m_armour, m_colour = monster
                        m_cur_prog, m_max_prog = m_prog
                        m_cur_health, m_max_health = m_health
                        m_cur_armour, m_max_armour = m_armour
                        m_fire_immune, m_air_immune, m_water_immune = m_colour
                        x_start, y_start = path[m_toward - 1]
                        x_end, y_end = path[m_toward]
                        x_start = x_start * 50 - 25
                        y_start = y_start * 50 + 175
                        x_end = x_end * 50 - 25
                        y_end = y_end * 50 + 175
                        x_dif = x_end - x_start
                        y_dif = y_end - y_start
                        m_x_pos = x_start + x_dif * m_cur_prog/m_max_prog
                        m_y_pos = y_start + y_dif * m_cur_prog/m_max_prog
                        
                        x_dist = m_x_pos - x
                        y_dist = m_y_pos - y
                        
                        # ******Incomplete Code!!!!!!
                        if x_dist ** 2 + y_dist ** 2 <= rng ** 2:
                            cur_charge = 0
                            # tower shoots monster
                            # calculate damage
                            hit_damage = random.randrange(min_damage, max_damage)
                            if typ == 'fire':
                                range_modifyer = 1 - ((x_dist ** 2 + y_dist ** 2)/rng **2)
                                hit_damage = int(hit_damage * range_modifyer)
                                hit_damage = max(hit_damage - m_cur_armour, 0)
                                hit_damage = int(hit_damage *(255- m_fire_immune)/255)
                                alpha = range_modifyer
                                beams.append([[x, y], [m_x_pos, m_y_pos], [255, 0, 0], alpha])
                                if not fire_hit:
                                    fire_hit_sound.play()
                                    fire_hit = True
                            elif typ == 'water':
                                hit_damage = max(hit_damage - m_cur_armour, 0)
                                hit_damage = int(hit_damage *(255- m_water_immune)/255)
                                m_cur_armour = max(m_cur_armour - level, 0)
                                monster_hit = True
                                beams.append([[x, y], [m_x_pos, m_y_pos], [0, 0, 255], 1])
                                water_hit_sound.play()
                            elif typ == 'air':
                                hit_damage = max(hit_damage - m_cur_armour, 0)
                                hit_damage = int(hit_damage *(255- m_air_immune)/255)
                                monster_hit = True
                                if random.randrange(100) < level:
                                    m_cur_prog, m_max_prog = m_prog
                                    m_prog = [m_cur_prog, m_max_prog + 1]
                                beams.append([[x, y], [m_x_pos, m_y_pos], [0, 255, 0], 1])
                                air_hit_sound.play()
                            elif typ == 'earth':
                                hit_damage = max(hit_damage - m_cur_armour, 0)
                                monster_hit = True
                                gold = gold + level
                                beams.append([[x, y], [m_x_pos, m_y_pos], [255, 255, 0], 1])
                                earth_hit_sound.play()
                            m_cur_health = max(m_cur_health - hit_damage, 0)
                            new_monsters.append([m_toward, m_prog, [m_cur_health, m_max_health], [m_cur_armour, m_max_armour], m_colour])
                        else:
                            new_monsters.append(monster)

                active_monsters = new_monsters
            new_towers.append([typ, pos, [cur_charge, max_charge], damage, rng, health, level])
    towers = new_towers
    
    new_monsters=[]
    for monster in active_monsters:
        toward, prog, health, armour, colour = monster
        cur_health, max_health = health
        cur_prog, max_prog = prog
        cur_armour, max_armour = armour
        if cur_health <= 0:
            earned_gold = int((math.sqrt(max_health)/5 + 15) * gold_modifier)
            gold = gold + earned_gold
            earned_score = max_health * max_armour
            score = score + earned_score
        else:
            monster_escaped = False
            cur_prog += 1
            if cur_prog == max_prog:
                cur_prog = 0
                toward +=1
                if toward == len(path):
                    monster_escape_sound.play()
                    if len(towers) == 0:
                        state = 2
                        update_timer.stop()
                        activate_timer.stop()
                        wave_timer.stop()
                    else:
                        new_towers = []
                        for tower in towers:
                            t_typ, t_pos, t_charge, t_damage, t_rng, t_health, t_level = tower
                            t_cur_health, t_max_health = t_health
                            t_x_pos, t_y_pos = t_pos
                            t_x = t_x_pos * 50 - 25
                            t_y = t_y_pos * 50 + 175
                            beams.append([[t_x, t_y+14], [t_x-20, t_y-20], [255, 0, 0], 1])
                            beams.append([[t_x, t_y-14], [t_x+20, t_y+20], [255, 255, 0], 1])
                            beams.append([[t_x, t_y+14], [t_x+20, t_y-20], [255, 0, 0], 1])
                            beams.append([[t_x, t_y-14], [t_x-20, t_y+20], [255, 255, 0], 1])
                            beams.append([[t_x+20, t_y-20], [t_x-20, t_y-20], [255, 0, 0], 1])
                            beams.append([[t_x+20, t_y+20], [t_x-20, t_y+20], [255, 255, 0], 1])
                            max_damage = max_armour + 10
                            damage = random.randrange(5, max_damage)
                            t_cur_health = max(t_cur_health - damage, 0)
                            new_towers.append([t_typ, t_pos, t_charge, t_damage, t_rng, [t_cur_health, t_max_health], t_level])
                        towers = new_towers
                    monster_escaped = True
                    toward = 1
            if monsters_loop or not monster_escaped:
                new_monsters.append([toward, [cur_prog, max_prog], health, armour, colour])
    active_monsters = new_monsters
       
def mouse_click(pos):
    global path_type
    global gold
    global gold_modifier
    global colour_min
    global colour_max
    global state
    global buttons_selected
    global towers
    global path
    global active_monsters
    global pending_monsters
    global score
    global paused
    global auto_repair
    global path_tiles
    global monsters_loop
    global wave
    
    clicked_button = get_button_clicked(buttons[state], pos)
    if state == 0:
        if clicked_button > -1:
            if clicked_button in [0, 1, 2]:
                for index in range(0, 3):
                    if index in buttons_selected:
                        buttons_selected.remove(index)
                buttons_selected.append(clicked_button)
                path_type = 2 - clicked_button
            elif clicked_button in [3, 4, 5]:
                for index in range(3, 6):
                    if index in buttons_selected:
                        buttons_selected.remove(index)
                buttons_selected.append(clicked_button)
                gold = (11 - clicked_button) * 100
                gold_modifier = 2 - (clicked_button * 0.25)
            elif clicked_button in [6, 7, 8]:
                for index in range(6, 9):
                    if index in buttons_selected:
                        buttons_selected.remove(index)
                buttons_selected.append(clicked_button)
                if clicked_button == 6:
                    colour_min = 0
                    colour_max = 128
                elif clicked_button == 7:
                    colour_min = 64
                    colour_max = 192
                elif clicked_button == 8:
                    colour_min = 128
                    colour_max = 256
            elif clicked_button == 9:
                buttons_selected = []
                path_tiles = random.randrange(4)
                generate_path(path_type)
                state = 1
                update_timer.start()
                wave_timer.start()
                new_wave()
            elif clicked_button == 10:
                if 10 in buttons_selected:
                    buttons_selected.remove(10)
                    auto_repair = False
                else:
                    buttons_selected.append(10)
                    auto_repair = True                    
            elif clicked_button == 11:
                if 11 in buttons_selected:
                    buttons_selected.remove(11)
                    monsters_loop = False
                else:
                    buttons_selected.append(11)
                    monsters_loop = True                    
    elif state == 1:
        if clicked_button >-1:
            if 0 <= clicked_button <= 5 or 7 <= clicked_button <= 9:
                if len(buttons_selected) == 0:
                    buttons_selected.append(clicked_button)
                else:
                    buttons_selected = []
            elif clicked_button == 6:
                wave_timer.stop()
                new_wave()
                wave_timer.start()
            elif clicked_button == 10:
                if paused:
                    wave_timer.start()
                    update_timer.start()
                    if len(pending_monsters) > 0:
                        activate_timer.start()
                    paused = False
                else:
                    wave_timer.stop()
                    update_timer.stop()
                    if len(pending_monsters) > 0:
                        activate_timer.stop()
                    paused = True
        else:
            x, y = pos
            x_block = x // 50 + 1
            y_block = y // 50 - 3
            if x_block > 0 and y_block > 0 and len(buttons_selected) > 0:
                if 0<= buttons_selected[0] <= 3:
                    typ, t_pos, charge, damage, rng, health, level, cost = base_towers[buttons_selected[0]]
                    t_pos = [x_block, y_block]
                    free_space = True
                    for tower in towers:
                        exist_pos = tower[1]
                        if t_pos == exist_pos:
                            free_space = False
                    if t_pos not in path and free_space:
                        if gold >= cost:
                            gold = gold - cost
                            towers.append([typ, t_pos, charge, damage, rng, health, level])
                elif buttons_selected[0] == 4:
                    # repair tower
                    sel_pos = [x_block, y_block]
                    for tower in towers:
                        exist_pos = tower[1]
                        if sel_pos == exist_pos:
                            health = tower[5]
                            cur_health, max_health = health
                            damage = max_health - cur_health
                            if gold>= damage:
                                gold = gold - damage
                                tower[5] = [max_health, max_health]
                elif buttons_selected[0] == 5:
                    # upgrade tower
                    sel_pos = [x_block, y_block]
                    for tower in towers:
                        exist_pos = tower[1]
                        if sel_pos == exist_pos:
                            typ, t_pos, charge, damage, rng, health, level = tower
                            if typ == 'fire':
                                cost = int(75 * (1.25) ** level)
                                if gold >= cost:
                                    gold = gold - cost
                                    cur_charge, max_charge = charge
                                    charge = [0, int(max_charge * 0.85 + 15)]
                                    min_damage, max_damage = damage
                                    damage =[int(min_damage * 1.25), int(max_damage * 1.3)]
                                    rng = rng + 5
                                    cur_health, max_health = health
                                    health = [int(max_health * 1.15), int(max_health * 1.15)]
                                    level = level + 1
                                    tower[2] = charge
                                    tower[3] = damage
                                    tower[4] = rng
                                    tower[5] = health
                                    tower[6] = level
                            elif typ == 'water':
                                cost = int(50 * (1.25) ** level)
                                if gold >= cost:
                                    gold = gold - cost
                                    cur_charge, max_charge = charge
                                    charge = [0, int(max_charge * 0.9 + 1.5)]
                                    min_damage, max_damage = damage
                                    damage =[int(min_damage * 1.3), int(max_damage * 1.3)]
                                    rng = rng + 10
                                    cur_health, max_health = health
                                    health = [int(max_health * 1.2), int(max_health * 1.2)]
                                    level = level + 1
                                    tower[2] = charge
                                    tower[3] = damage
                                    tower[4] = rng
                                    tower[5] = health
                                    tower[6] = level
                            elif typ == 'air':
                                cost = int(25 * (1.25) ** level)
                                if gold >= cost:
                                    gold = gold - cost
                                    cur_charge, max_charge = charge
                                    charge = [0, int(max_charge * 0.8 + 1)]
                                    min_damage, max_damage = damage
                                    damage =[int(min_damage * 1.15), int(max_damage * 1.15)]
                                    rng = rng + 15
                                    cur_health, max_health = health
                                    health = [int(max_health * 1.1), int(max_health * 1.1)]
                                    level = level + 1
                                    tower[2] = charge
                                    tower[3] = damage
                                    tower[4] = rng
                                    tower[5] = health
                                    tower[6] = level
                            elif typ == 'earth':
                                cost = int(100 * (1.25) ** level)
                                if gold >= cost:
                                    gold = gold - cost
                                    cur_charge, max_charge = charge
                                    charge = [0, int(max_charge * 0.9 + 6)]
                                    min_damage, max_damage = damage
                                    damage =[int(min_damage * 1.1), int(max_damage * 1.3)]
                                    rng = rng + 10
                                    cur_health, max_health = health
                                    health = [int(max_health * 1.25), int(max_health * 1.25)]
                                    level = level + 1
                                    tower[2] = charge
                                    tower[3] = damage
                                    tower[4] = rng
                                    tower[5] = health
                                    tower[6] = level
            buttons_selected = []
    elif state == 2:
        if clicked_button ==0:
            path = []
            towers = []
            active_monsters = []
            pending_monsters = []
            buttons_selected = [0, 3, 6]
            state = 0
            path_type = 2
            gold = 800
            gold_modifier = 1.25
            colour_min = 0
            colour_max = 128
            score = 0
            wave = 0
            auto_repair = False
            paused = False
            monsters_loop = False

def get_button_clicked(buttons, pos):
    for button in buttons:
        if button[0] <= pos[0] <= button[2] and button[1] <= pos[1] <= button[3]:
            return buttons.index(button)
        
    return -1
        
# functions to draw the game
def draw_monsters(canvas):
    for monster in active_monsters:
        toward, prog, health, armour, colour = monster
        cur_prog, max_prog = prog
        cur_health, max_health = health
        
        x_start, y_start = path[toward - 1]
        x_end, y_end = path[toward]
        x_start = x_start * 50 - 25
        y_start = y_start * 50 + 175
        x_end = x_end * 50 - 25
        y_end = y_end * 50 + 175
        x_dif = x_end - x_start
        y_dif = y_end - y_start
        x_pos = x_start + x_dif * cur_prog/max_prog
        y_pos = y_start + y_dif * cur_prog/max_prog
        rad = 20.0 * float(cur_health)/max_health + random.randrange(2)
        canvas.draw_circle([x_pos, y_pos], rad, 4, rgb_to_hex(colour))
    
def draw_towers(canvas):
    for tower in towers:
        typ, pos, charge, damage, rng, health, level = tower
        x_pos, y_pos = pos
        cur_health, max_health = health
        cur_charge, max_charge = charge
        g_col = int(255 * cur_health/ max_health)
        r_col = 255 - g_col
        x = x_pos * 50 - 25
        y = y_pos * 50 + 175
        x_cent = 50 * min(level, 20) - 25
        canvas.draw_image(tower_image, [x_cent,25],[50,50],[x,y],[50,50])

        
        '''
        if level == 1:
            canvas.draw_circle([x, y], 16, 2, '#888888')
        elif level == 2:
            canvas.draw_circle([x, y], 16, 2, '#888888')
            canvas.draw_circle([x, y], 19, 2, '#888888')
        elif level == 3:
            canvas.draw_circle([x, y], 16, 2, '#888888')
            canvas.draw_circle([x, y], 19, 2, '#888888')
            canvas.draw_circle([x, y], 22, 2, '#888888')
        elif level == 4:
            canvas.draw_polygon([[x-23,y-23], [x, y-16], [x+23,y-23], [x+16,y], [x+23,y+23], [x, y+16], [x-23, y+23], [x-16,y]], 2, '#888888')
            canvas.draw_circle([x, y], 16, 2, '#888888')
        elif level == 5:
            canvas.draw_polygon([[x-23,y-23], [x, y-16], [x+23,y-23], [x+16,y], [x+23,y+23], [x, y+16], [x-23, y+23], [x-16,y]], 2, '#888888')
            canvas.draw_line([x-23, y-23], [x-12,y-12],2,'#888888')
            canvas.draw_line([x+23, y-23], [x+12,y-12],2,'#888888')
            canvas.draw_line([x-23, y+23], [x-12,y+12],2,'#888888')
            canvas.draw_line([x+23, y+23], [x+12,y+12],2,'#888888')
            canvas.draw_circle([x, y], 16, 2, '#888888')
        elif level == 6:
            canvas.draw_polygon([[x-23,y-23], [x, y-16], [x+23,y-23], [x+16,y], [x+23,y+23], [x, y+16], [x-23, y+23], [x-16,y]], 2, '#888888')
            canvas.draw_polygon([[x-16,y-16], [x-16,y+16], [x+16,y+16], [x+16, y-16]], 2, '#888888')
            canvas.draw_circle([x, y], 16, 2, '#888888')
        elif level == 7:
            canvas.draw_polygon([[x-23,y-23], [x, y-16], [x+23,y-23], [x+16,y], [x+23,y+23], [x, y+16], [x-23, y+23], [x-16,y]], 2, '#888888')
            canvas.draw_polygon([[x-23,y], [x-16, y-16], [x,y-23], [x+16,y-16], [x+23,y], [x+16, y+16], [x, y+23], [x-16,y+16]], 2, '#888888')
            canvas.draw_polygon([[x-16,y-16], [x-16,y+16], [x+16,y+16], [x+16, y-16]], 2, '#888888')
            canvas.draw_circle([x, y], 16, 2, '#888888')
        elif level == 8:
            canvas.draw_polygon([[x-23,y-23], [x-13, y-23], [x, y-16], [x+13, y-23], [x+23,y-23], [x+23, y-13], [x+16,y], [x+23, y+13], [x+23,y+23], [x+13, y+23], [x, y+16], [x-13, y+23], [x-23, y+23], [x-23, y+13], [x-16,y], [x-23, y-13]], 2, '#888888')
            canvas.draw_circle([x, y], 16, 2, '#888888')
        elif level == 9:
            canvas.draw_polygon([[x-23,y-23], [x-13, y-23], [x, y-16], [x+13, y-23], [x+23,y-23], [x+23, y-13], [x+16,y], [x+23, y+13], [x+23,y+23], [x+13, y+23], [x, y+16], [x-13, y+23], [x-23, y+23], [x-23, y+13], [x-16,y], [x-23, y-13]], 2, '#888888')
            canvas.draw_circle([x+16, y+16], 3, 2, '#999999')
            canvas.draw_circle([x-16, y+16], 3, 2, '#999999')
            canvas.draw_circle([x+16, y-16], 3, 2, '#999999')
            canvas.draw_circle([x-16, y-16], 3, 2, '#999999')
            canvas.draw_circle([x, y], 16, 2, '#888888')
        elif level == 10:
            canvas.draw_polygon([[x-23,y-23], [x-13, y-23], [x, y-16], [x+13, y-23], [x+23,y-23], [x+23, y-13], [x+16,y], [x+23, y+13], [x+23,y+23], [x+13, y+23], [x, y+16], [x-13, y+23], [x-23, y+23], [x-23, y+13], [x-16,y], [x-23, y-13]], 2, '#888888')
            canvas.draw_polygon([[x-23,y], [x,y-23], [x+23,y], [x, y+23]], 2, '#888888')
            canvas.draw_circle([x, y], 16, 2, '#888888')
        elif level == 11:
            canvas.draw_polygon([[x-23,y-23], [x-13, y-23], [x, y-16], [x+13, y-23], [x+23,y-23], [x+23, y-13], [x+16,y], [x+23, y+13], [x+23,y+23], [x+13, y+23], [x, y+16], [x-13, y+23], [x-23, y+23], [x-23, y+13], [x-16,y], [x-23, y-13]], 2, '#888888')
            canvas.draw_circle([x+16, y+16], 3, 2, '#999999')
            canvas.draw_circle([x-16, y+16], 3, 2, '#999999')
            canvas.draw_circle([x+16, y-16], 3, 2, '#999999')
            canvas.draw_circle([x-16, y-16], 3, 2, '#999999')
            canvas.draw_polygon([[x-23,y], [x,y-23], [x+23,y], [x, y+23]], 2, '#888888')
            canvas.draw_circle([x, y], 16, 2, '#888888')
        elif level == 12:
            canvas.draw_polygon([[x-23,y-23], [x-8, y-23], [x, y-16], [x+8, y-23], [x+23,y-23], [x+23, y-8], [x+16,y], [x+23, y+8], [x+23,y+23], [x+8, y+23], [x, y+16], [x-8, y+23], [x-23, y+23], [x-23, y+8], [x-16,y], [x-23, y-8]], 2, '#888888')
            canvas.draw_circle([x, y], 16, 2, '#888888')
        elif level == 13:
            canvas.draw_polygon([[x-23,y-23], [x-8, y-23], [x, y-16], [x+8, y-23], [x+23,y-23], [x+23, y-8], [x+16,y], [x+23, y+8], [x+23,y+23], [x+8, y+23], [x, y+16], [x-8, y+23], [x-23, y+23], [x-23, y+8], [x-16,y], [x-23, y-8]], 2, '#888888')
            canvas.draw_polygon([[x-19, y-19], [x-19, y-10], [x-10, y-19]], 2, '#999999')
            canvas.draw_polygon([[x+19, y-19], [x+19, y-10], [x+10, y-19]], 2, '#999999')
            canvas.draw_polygon([[x-19, y+19], [x-19, y+10], [x-10, y+19]], 2, '#999999')
            canvas.draw_polygon([[x+19, y+19], [x+19, y+10], [x+10, y+19]], 2, '#999999')
            canvas.draw_circle([x, y], 16, 2, '#888888')
        elif level == 14:
            canvas.draw_polygon([[x, y+16], [x-9, y+23], [x+9, y+23]], 2, '#999999')
            canvas.draw_polygon([[x, y-16], [x-9, y-23], [x+9, y-23]], 2, '#999999')
            canvas.draw_polygon([[x-16, y], [x-23, y+9], [x-23, y-9]], 2, '#999999')
            canvas.draw_polygon([[x+16, y], [x+23, y+9], [x+23, y-9]], 2, '#999999')
            canvas.draw_polygon([[x+12, y+12], [x+11, y+22], [x+22, y+11]], 2, '#999999')
            canvas.draw_polygon([[x-12, y+12], [x-11, y+22], [x-22, y+11]], 2, '#999999')
            canvas.draw_polygon([[x+12, y-12], [x+11, y-22], [x+22, y-11]], 2, '#999999')
            canvas.draw_polygon([[x-12, y-12], [x-11, y-22], [x-22, y-11]], 2, '#999999')
            canvas.draw_circle([x, y], 16, 2, '#888888')
        elif level == 15:
            canvas.draw_polygon([[x-23,y], [x,y-23], [x+23,y], [x, y+23]], 2, '#888888')
            canvas.draw_polygon([[x-16,y-16], [x-16,y+16], [x+16,y+16], [x+16, y-16]], 2, '#888888')
        else:
            canvas.draw_polygon([[x-23,y], [x,y-23], [x+23,y], [x, y+23]], 2, '#888888')
            canvas.draw_polygon([[x-16,y-16], [x-16,y+16], [x+16,y+16], [x+16, y-16]], 2, '#888888')
            canvas.draw_circle([x+21, y+9], 2, 1, '#999999')
            canvas.draw_circle([x+9, y+21], 2, 1, '#999999')
            canvas.draw_circle([x-21, y+9], 2, 1, '#999999')
            canvas.draw_circle([x-9, y+21], 2, 1, '#999999')
            canvas.draw_circle([x+21, y-9], 2, 1, '#999999')
            canvas.draw_circle([x+9, y-21], 2, 1, '#999999')
            canvas.draw_circle([x-21, y-9], 2, 1, '#999999')
            canvas.draw_circle([x-9, y-21], 2, 1, '#999999')
        '''        
        canvas.draw_circle([x,y], 14, 2, rgb_to_hex([r_col,g_col,0]))
        col = int(255 * cur_charge/max_charge)
        if typ == 'fire':
            cols = rgb_to_hex([col, 0, 0])
            canvas.draw_circle([x,y], 10, 2, cols)
            canvas.draw_polygon([[x, y-10], [x + 8, y + 5], [x - 8, y + 5]], 2, cols)
            upgrade_cost = int(75 * 1.25 ** level)
            rng_col = '880000'
        elif typ == 'water':
            cols = rgb_to_hex([0, 0, col])
            canvas.draw_circle([x,y], 10, 2, cols)
            canvas.draw_polygon([[x, y+10], [x + 8, y - 5], [x - 8, y - 5]], 2, cols)
            upgrade_cost = int(50 * 1.25 ** level)
            rng_col = '000088'
        elif typ == 'air':
            cols = rgb_to_hex([0, col, 0])
            canvas.draw_circle([x,y], 10, 2, cols)
            canvas.draw_polygon([[x, y-10], [x + 8, y + 5], [x - 8, y + 5]], 2, cols)
            canvas.draw_line([x + 8, y - 5],[x - 8, y - 5],2, cols)
            upgrade_cost = int(25 * 1.25 ** level)
            rng_col = '008800'
        elif typ == 'earth':
            cols = rgb_to_hex([col, col, 0])
            canvas.draw_circle([x,y], 10, 2, cols)
            canvas.draw_line([x, y + 10],[x, y - 10],2, cols)
            canvas.draw_line([x + 10, y],[x - 10, y],2, cols)
            upgrade_cost = int(100 * 1.25 ** level)
            rng_col = '888800'
        repair_cost = max_health - cur_health
        if len(buttons_selected) > 0:
            if 4 in buttons_selected:
                if gold >= repair_cost:
                    canvas.draw_text(str(repair_cost), [x-20, y+20], 14, '#FFFFFF')
                else:
                    canvas.draw_text(str(repair_cost), [x-20, y+20], 14, '#FF0000')
            elif 5 in buttons_selected:
                if gold >= upgrade_cost:
                    canvas.draw_text(str(upgrade_cost), [x-20, y+20], 14, '#FFFFFF')
                else:
                    canvas.draw_text(str(upgrade_cost), [x-20, y+20], 14, '#FF0000')
            elif 7 in buttons_selected:
                # show tower range
                canvas.draw_circle([x,y], rng, 1, rng_col)
            elif 8 in buttons_selected:
                # show tower damage
                canvas.draw_text(str(damage[0]), [x-20, y], 14, '#FFFFFF')
                canvas.draw_text(str(damage[1]), [x-20, y+20], 14, '#FFFFFF')
            elif 9 in buttons_selected:
                # show tower charge time
                canvas.draw_text(str(max_charge), [x-20, y+20], 14, '#FFFFFF')
        
    # fire tower button
    x = 62
    y = 62
    canvas.draw_polygon([[x-23,y], [x,y-23], [x+23,y], [x, y+23]], 2, '#888888')
    canvas.draw_polygon([[x-16,y-16], [x-16,y+16], [x+16,y+16], [x+16, y-16]], 2, '#888888')
    cols = rgb_to_hex([255, 0, 0])
    canvas.draw_circle([x,y], 10, 2, cols)
    canvas.draw_polygon([[x, y-10], [x + 8, y + 5], [x - 8, y + 5]], 2, cols)

    # water tower button
    x = 162
    y = 62
    canvas.draw_polygon([[x-23,y], [x,y-23], [x+23,y], [x, y+23]], 2, '#888888')
    canvas.draw_polygon([[x-16,y-16], [x-16,y+16], [x+16,y+16], [x+16, y-16]], 2, '#888888')
    cols = rgb_to_hex([0, 0, 255])
    canvas.draw_circle([x,y], 10, 2, cols)
    canvas.draw_polygon([[x, y+10], [x + 8, y - 5], [x - 8, y - 5]], 2, cols)

    # air tower button
    x = 262
    y = 62
    canvas.draw_polygon([[x-23,y], [x,y-23], [x+23,y], [x, y+23]], 2, '#888888')
    canvas.draw_polygon([[x-16,y-16], [x-16,y+16], [x+16,y+16], [x+16, y-16]], 2, '#888888')
    cols = rgb_to_hex([0, 255, 0])
    canvas.draw_circle([x,y], 10, 2, cols)
    canvas.draw_polygon([[x, y-10], [x + 8, y + 5], [x - 8, y + 5]], 2, cols)
    canvas.draw_line([x + 8, y - 5],[x - 8, y - 5],2, cols)

    # earth tower button
    x = 362
    y = 62
    canvas.draw_polygon([[x-23,y], [x,y-23], [x+23,y], [x, y+23]], 2, '#888888')
    canvas.draw_polygon([[x-16,y-16], [x-16,y+16], [x+16,y+16], [x+16, y-16]], 2, '#888888')
    cols = rgb_to_hex([255, 255, 0])
    canvas.draw_circle([x,y], 10, 2, cols)
    canvas.draw_line([x, y + 10],[x, y - 10],2, cols)
    canvas.draw_line([x + 10, y],[x - 10, y],2, cols)
    
    # repair button
    x = 462
    y = 62
    canvas.draw_circle([x,y], 14, 2, '#AAAA00')
    canvas.draw_polygon([[x-23,y-19], [x-19,y-23], [x+23,y+19], [x+19, y+23]], 2, '#888888', '#000000')
    canvas.draw_polygon([[x+23,y-19], [x+19,y-23], [x-23,y+19], [x-19, y+23]], 2, '#888888', '#000000')
    canvas.draw_polygon([[x-9,y-23], [x-2,y-16], [x-16,y-2], [x-23, y-9]], 2, '#888888', '#000000')
    canvas.draw_polygon([[x+9,y-23], [x+2,y-16], [x+16,y-2], [x+23, y-9]], 2, '#888888', '#000000')
    
    # upgrade button
    x = 562
    y = 62
    
    # new wave button
    x = 662
    y = 62
    canvas.draw_circle([x-9,y], 14, 2, '#00FF00')
    canvas.draw_circle([x,y], 14, 2, '#0000FF')
    canvas.draw_circle([x+9,y], 14, 2, '#FF0000')
    
    # range display button
    x = 742
    y = 42
    canvas.draw_circle([x,y], 13, 1, '#00FFFF')
    canvas.draw_line([x,y], [x+13,y], 1, 'FFFF00')
    
    # damage display button
    x = 782
    y = 42
    canvas.draw_circle([x+9,y], 4, 1, '#FF8844')
    canvas.draw_line([x-12,y], [x+9,y], 1, 'FFFF00')    
    
    # charge time display button
    x = 742
    y = 82
    canvas.draw_circle([x,y], 13, 1, '#00FFFF')
    canvas.draw_line([x,y], [x,y-10], 1, 'FFFF00')    
    canvas.draw_line([x,y], [x+5,y], 1, 'FFFF00')    
    
    # pause button
    x = 782
    y = 82
    if paused:
        canvas.draw_polygon([[x-5,y-12], [x-5,y+12], [x+13,y]], 1, '#FFFFFF', '#FFFFFF')
    else:
        canvas.draw_polygon([[x-13,y-13], [x-13,y+13], [x-7,y+13], [x-7, y-13]], 1, '#FFFFFF', '#FFFFFF')
        canvas.draw_polygon([[x+13,y-13], [x+13,y+13], [x+7,y+13], [x+7, y-13]], 1, '#FFFFFF', '#FFFFFF')

def draw_beams(canvas):
    for beam in beams:
        start, end, colour, alpha = beam
        canvas.draw_line(start, end, 2, rgb_to_hex(colour, alpha))
    
def draw_path(canvas):
    for block in path:
        x, y = block
        mode = (x + y) % 2
        colour =['#444444', '#222222']
        x = x * 50 - 25
        y = y * 50 + 175
        #canvas.draw_polygon([(x - 23, y - 23), (x - 23, y + 23), (x + 23, y + 23), (x + 23, y - 23)], 0, colour[mode], colour[mode])
        #canvas.draw_image(path_image, [25,25],[50,50],[x,y],[50,50])
        idx = path.index(block)
        y_cent = path_tiles * 50 + 25
        if path_walls[idx] == [0,1]:
            canvas.draw_image(path_image, [275,y_cent],[50,50],[x,y],[50,50])
        elif path_walls[idx] == [0,2]:
            canvas.draw_image(path_image, [225,y_cent],[50,50],[x,y],[50,50])
        elif path_walls[idx] == [0,3]:
            canvas.draw_image(path_image, [175,y_cent],[50,50],[x,y],[50,50])
        elif path_walls[idx] == [1,2]:
            canvas.draw_image(path_image, [125,y_cent],[50,50],[x,y],[50,50])
        elif path_walls[idx] == [1,3]:
            canvas.draw_image(path_image, [75,y_cent],[50,50],[x,y],[50,50])
        elif path_walls[idx] == [2,3]:
            canvas.draw_image(path_image, [25,y_cent],[50,50],[x,y],[50,50])
                    
def draw_buttons(buttons, canvas):
    for button in buttons:
        rect = [[button[0],button[1]],[button[2],button[1]], [button[2],button[3]],[button[0],button[3]]]
        colour = rgb_to_hex(button[5])
        canvas.draw_polygon(rect, 5, colour)
        if buttons.index(button) in buttons_selected:
            canvas.draw_polygon(rect, 3, 'White')
        size = 16
        x_pos = (button[0] + button[2])/2 - len(button[4])* size/4
        y_pos = (button[1] + button[3])/2 + size/2
        pos =[x_pos, y_pos]
        canvas.draw_text(button[4], pos, size, colour)

# Handler to draw on canvas
def draw(canvas):
    
    draw_buttons(buttons[state], canvas)

    if state == 0:
        canvas.draw_text('Path Length', [25,50], 16, "Blue")
        canvas.draw_text('Gold', [225,50], 16, "Yellow")
        canvas.draw_text('Resistance', [425,50], 16, "Green")
        
    elif state == 1:
        canvas.draw_image(background_image, [400, 200], [800,400], [400, 400], [800,400])
        canvas.draw_line((0, 185), (800, 185), 5, 'White')
        canvas.draw_line((0, 150), (800, 150), 5, 'White')
        canvas.draw_line((0, 185), (800, 185), 3, '#AAAAAA')
        canvas.draw_line((0, 150), (800, 150), 3, '#AAAAAA')
        canvas.draw_line((0, 185), (800, 185), 1, '#555555')
        canvas.draw_line((0, 150), (800, 150), 1, '#555555')
        canvas.draw_text('Fire Tower', (25,120), 12, 'White')
        canvas.draw_text('150 Gold', (25,140), 12, 'White')
        canvas.draw_text('Water Tower', (125,120), 12, 'White')
        canvas.draw_text('100 Gold', (125,140), 12, 'White')
        canvas.draw_text('Air Tower', (225,120), 12, 'White')
        canvas.draw_text('50 Gold', (225,140), 12, 'White')
        canvas.draw_text('Earth Tower', (325,120), 12, 'White')
        canvas.draw_text('200 Gold', (325,140), 12, 'White')
        canvas.draw_text('Repair', (425,120), 12, 'White')
        canvas.draw_text('Upgrade', (525,120), 12, 'White')
        canvas.draw_text('Next Wave', (625,120), 12, 'White')
        canvas.draw_text('Gold: ' + str(gold), (5,175), 18, '#FFFF00')
        canvas.draw_text('Monsters: ' + str(len(active_monsters)) +'/' +str(len(pending_monsters)), (200,175), 18, '#FF0000')
        canvas.draw_text('Wave: ' + str(wave), (395, 175), 18, '#0000FF')
        canvas.draw_text('Score: ' + str(score), (590, 175), 18, '#AAAAAA')
        draw_path(canvas)
        draw_monsters(canvas)
        draw_towers(canvas)
        draw_beams(canvas)
        
    elif state == 2:
        canvas.draw_text('Game Over', [50,112], 36, "Red")
        canvas.draw_text('Final score:' + str(score), [50,150], 36, "Yellow")

# Create a frame and assign callbacks to event handlers
frame = simplegui.create_frame("Home", 800, 600)
frame.set_draw_handler(draw)
update_timer = simplegui.create_timer(25, update)
wave_timer = simplegui.create_timer(45000, new_wave)
activate_timer = simplegui.create_timer(1000, activate_monster)
frame.set_mouseclick_handler(mouse_click)

path_dir = "http://commondatastorage.googleapis.com/codeskulptor-demos/towerdefence_assets/"
#load images & sounds
#path_image=simplegui.load_image("http://dl.dropbox.com/s/zjr2nmge7re9ztx/Path.png")
path_image=simplegui.load_image(path_dir + "Path.gif")

tower_image=simplegui.load_image(path_dir + "Towers.gif")
if random.randrange(3) == 0:
    background_image=simplegui.load_image(path_dir + "Background_Sand.png")
elif random.randrange(2) == 0:
    background_image=simplegui.load_image(path_dir + "Background_Moon.png")
else:    
    background_image=simplegui.load_image(path_dir + "Background_Ice.png")
    
water_hit_sound = simplegui.load_sound(path_dir + "Water_hit.wav")
water_hit_sound.set_volume(0.3)
fire_hit_sound = simplegui.load_sound(path_dir + "fire.wav")
fire_hit_sound.set_volume(0.1)
air_hit_sound = simplegui.load_sound(path_dir + "Air.wav")
air_hit_sound.set_volume(0.3)
earth_hit_sound = simplegui.load_sound(path_dir + "Rock.mp3")
earth_hit_sound.set_volume(0.5)
monster_escape_sound = simplegui.load_sound(path_dir + "lightning.wav")
monster_escape_sound.set_volume(0.5)

# Start the frame animation
towers = []
    
buttons_selected = [0, 3, 6]
state = 0
wave = 0
# set up game difficulty constants
auto_repair = False
paused = False
monsters_loop = False
path_type = 2
gold = 800
gold_modifier = 1.25
colour_min = 0
colour_max = 128
score = 0

frame.start()