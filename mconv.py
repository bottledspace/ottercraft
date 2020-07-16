#!/usr/local/opt/python@3.8/bin/python3
# Usage:
#   mconv MODE input
#
#   MODE
#    c - Generates members, init and render methods.
#    a - Generates poseAction method.
#

import csv
import sys

mode     = sys.argv[1]
filename = sys.argv[2]

action_name = filename.replace("_", '.').split(".")[-2].capitalize()

objects = {}
with open(filename, 'r') as file:
    reader = csv.DictReader(file, delimiter=',')
    for row in reader:
        obj = row['object']
        if obj not in objects:
            objects[obj] = []
        psr  = [row['posx'], row['posy'], row['posz']]
        psr += [row['scalex'], row['scaley'], row['scalez']]
        psr += [row['roll'], row['pitch'], row['yaw']]
        objects[obj] += [tuple(map(float, psr))]

if 'c' in mode:
    for name in objects:
        print('protected ModelRenderer %s;' % name)
    print('private void init() {')
    for name,frames in objects.items():
        sx = abs(0.5*round(2*frames[0][3]))
        sy = abs(0.5*round(2*frames[0][4]))
        sz = abs(0.5*round(2*frames[0][5]))
        print('  this.%s = new ModelRenderer(this);' % name)
        print('  this.%s.addBox(%.2ff,%.2ff,%.2ff,%.2ff,%.2ff,%.2ff);'
                % (name,-sx,-sz,-sy,sx*2,sz*2,sy*2))
    print('}')
    print('public void render(MatrixStack ms, IVertexBuilder b, '
        + 'int f, int g, float red, float green, float blue, float alpha) {')
    for name in objects:
        print('  this.%s.render(ms, b, f, g, red, green, blue, alpha);' % name)
    print('}')

if 'a' in mode:
    print('protected void pose%s(int frame) {' % action_name)
    for name,frames in objects.items():
        print('  final float[] %s_rotpos = new float[]{' % name)
        for frame in frames:
            print('    %.2ff,%.2ff,%.2ff,%.2ff,%.2ff,%.2ff,'
                    % (frame[0:3] + frame[6:9]))
        print('  };')
        print('  this.%s.rotateAngleX   = %s_rotpos[frame*6+3];' % (name, name))
        print('  this.%s.rotateAngleY   = %s_rotpos[frame*6+5];' % (name, name))
        print('  this.%s.rotateAngleZ   = %s_rotpos[frame*6+4];' % (name, name))
        print('  this.%s.rotationPointX = %s_rotpos[frame*6];'   % (name, name))
        print('  this.%s.rotationPointY = 24-%s_rotpos[frame*6+2];' % (name, name))
        print('  this.%s.rotationPointZ = %s_rotpos[frame*6+1];' % (name, name))
    print('}')