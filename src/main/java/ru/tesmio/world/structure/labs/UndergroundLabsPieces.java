package ru.tesmio.world.structure.labs;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Tesmio
 */
public class UndergroundLabsPieces {

    private static final ResourceLocation concertHall = new ResourceLocation("soviet:proc_lab/fragment_concert_hall");

    private static final ResourceLocation stairsTop = new ResourceLocation("soviet:proc_lab/fragment_stairs_top");
    private static final ResourceLocation stairsMid = new ResourceLocation("soviet:proc_lab/fragment_stairs_mid");
    private static final ResourceLocation stairsDown = new ResourceLocation("soviet:proc_lab/fragment_stairs_down");
    private static final ResourceLocation tripleFragment = new ResourceLocation("soviet:proc_lab/fragment_triple");
    private static final ResourceLocation tripleFragmentMirrored = new ResourceLocation("soviet:proc_lab/fragment_triple_mir");
    private static final ResourceLocation quadFragment = new ResourceLocation("soviet:proc_lab/fragment_quad");
    private static final ResourceLocation cornerFragment = new ResourceLocation("soviet:proc_lab/fragment_corner");
    private static final ResourceLocation cornerFragmentMirrored = new ResourceLocation("soviet:proc_lab/fragment_corner_mir");
    private static final ResourceLocation linearFragment1 = new ResourceLocation("soviet:proc_lab/fragment_linear1");
    private static final ResourceLocation linearFragment2 = new ResourceLocation("soviet:proc_lab/fragment_linear2");
    private static final ResourceLocation linearFragment3 = new ResourceLocation("soviet:proc_lab/fragment_linear3");
    private static final ResourceLocation linearFragment4 = new ResourceLocation("soviet:proc_lab/fragment_linear4");

    static IStructurePieceType UG_LABS_PIECES = IStructurePieceType.register(UndergroundLabsPieces.Piece::new, "soviet:proc_lab");

    private static final Map<ResourceLocation, BlockPos> POOL = new HashMap<>();


    public static void putValueInMap() {
        POOL.put(linearFragment1, BlockPos.ZERO);
        POOL.put(tripleFragment, BlockPos.ZERO);
        POOL.put(tripleFragmentMirrored, BlockPos.ZERO);
        POOL.put(quadFragment, BlockPos.ZERO);
        POOL.put(cornerFragment, BlockPos.ZERO);
        POOL.put(cornerFragmentMirrored, BlockPos.ZERO);
        POOL.put(linearFragment2, BlockPos.ZERO);
        POOL.put(linearFragment3, BlockPos.ZERO);
        POOL.put(linearFragment4, BlockPos.ZERO);
        POOL.put(stairsMid, BlockPos.ZERO);
        POOL.put(stairsTop, BlockPos.ZERO);
        POOL.put(stairsDown, BlockPos.ZERO);
        POOL.put(concertHall, BlockPos.ZERO);
    }
    static int lenthFragment = 5;
    public static void addPieces(TemplateManager tm, BlockPos bPos, List<StructurePiece> listPieces) {
        finalBuildCorridors(tm, bPos, listPieces);
  //      finalBuildStairs(tm, bPos, listPieces);
  //      finalBuildRooms(tm, bPos, listPieces);
    }

    public static void finalBuildRooms(TemplateManager tm, BlockPos bPos, List<StructurePiece> listPieces) {
        listPieces.add(new UndergroundLabsPieces.Piece(tm, concertHall, bPos, Rotation.NONE,Mirror.NONE, -20,-9));
    }
    public static void finalBuildStairs(TemplateManager tm, BlockPos bPos, List<StructurePiece> listPieces) {
        listPieces.add(new UndergroundLabsPieces.Piece(tm, stairsDown, bPos, Rotation.NONE,Mirror.NONE, 8,4));
        UndergroundLabsPieces.Piece lf = (UndergroundLabsPieces.Piece) listPieces.get(listPieces.size() - 1);
        listPieces.add(new UndergroundLabsPieces.Piece(tm, stairsMid, bPos, Rotation.NONE,Mirror.NONE, 8,4));
        listPieces.add(new UndergroundLabsPieces.Piece(tm, stairsTop, bPos, Rotation.NONE,Mirror.NONE, 8,-1));
    }


    public static void finalBuildCorridors(TemplateManager tm, BlockPos bPos, List<StructurePiece> listPieces) {
        ThreadLocalRandom tr = ThreadLocalRandom.current();
        if(tr.nextInt(8) < 4) {
             genQuadNode(tm, bPos, listPieces, Direction.NORTH, BlockPos.ZERO);
            genSouthWing(tm, bPos, listPieces);
//    genNorthWing(tm, bPos, listPieces);
//    genWestWing(tm, bPos, listPieces);
//    genEastWing(tm, bPos, listPieces);


}
    }

    public static BlockPos genCorridor(TemplateManager tm, BlockPos p, List<StructurePiece> l, Direction.Axis ax, int dirCorrection, int xCorrection, int zCorrection) {
        ThreadLocalRandom tr = ThreadLocalRandom.current();
        int randLin = tr.nextInt(6, 14);
        ResourceLocation[] linear = { linearFragment1, linearFragment2, linearFragment3, linearFragment4 };
        int correction = (ax == Direction.Axis.Z) ? zCorrection : xCorrection;
        Rotation rotation = (ax == Direction.Axis.X) ? Rotation.CLOCKWISE_90 : Rotation.NONE;

        // Начальный вызов рекурсивного метода
        return generateCorridorRecursive(tm, p, l, ax, dirCorrection, xCorrection, zCorrection, linear, rotation, correction, 4, randLin * lenthFragment);
    }

    private static BlockPos generateCorridorRecursive(TemplateManager tm, BlockPos p, List<StructurePiece> l, Direction.Axis ax, int dirCorrection, int xCorrection, int zCorrection, ResourceLocation[] linear, Rotation rotation, int correction, int currentPos, int maxPos) {
        if (currentPos >= maxPos) {
            // Возвращаем объект BlockPos с последней позицией
            int finalX = (ax == Direction.Axis.X) ? correction + (currentPos - lenthFragment) * dirCorrection : p.getX();
            int finalZ = (ax == Direction.Axis.Z) ? correction + (currentPos - lenthFragment) * dirCorrection : p.getZ();
            return new BlockPos(finalX, p.getY(), finalZ);
        }

        int pos = correction + currentPos * dirCorrection;
        l.add(new UndergroundLabsPieces.Piece(tm, linear[ThreadLocalRandom.current().nextInt(linear.length)], p, rotation, Mirror.NONE, (ax == Direction.Axis.X) ? pos : xCorrection, (ax == Direction.Axis.Z) ? pos : zCorrection));

        // Проверяем, не является ли текущая позиция последней
        if (currentPos + lenthFragment >= maxPos) {
            // Если да, возвращаем объект BlockPos с текущей позицией и завершаем рекурсию
            return new BlockPos((ax == Direction.Axis.X) ? pos : p.getX(), p.getY(), (ax == Direction.Axis.Z) ? pos : p.getZ());
        }

        // Рекурсивный вызов для следующего фрагмента
        return generateCorridorRecursive(tm, p, l, ax, dirCorrection, xCorrection, zCorrection, linear, rotation, correction, currentPos + lenthFragment, maxPos);
    }


    public static void genSouthWing(TemplateManager tm, BlockPos bPos, List<StructurePiece> listPieces) {
        BlockPos finishL0;// сюда записывается конечная координата коридора первого уровня
        {
            genCorridorSouth(tm, bPos, listPieces);//Генерация первого коридора на юг рандомной длины
            finishL0 = genCorridorSouth(tm, bPos, listPieces);//получение конечной координаты сгенерированного коридора
            genQuadNode(tm, bPos, listPieces, Direction.SOUTH, finishL0); //Генерация на конечной координате четырехвходной ноды

        }
    }
//    public static void genNorthWing(TemplateManager tm, BlockPos bPos, List<StructurePiece> listPieces) {
//        int finishL0;// сюда записывается конечная координата коридора первого уровня
//        {
//            genCorridorNorth(tm, bPos, listPieces);//Генерация первого коридора на юг рандомной длины
//            finishL0 = genCorridorNorth(tm, bPos, listPieces);//получение конечной координаты сгенерированного коридора
//            genQuadNode(tm, bPos, listPieces, Direction.NORTH, finishL0); //Генерация на конечной координате четырехвходной ноды
//        }
//    }
//    public static void genWestWing(TemplateManager tm, BlockPos bPos, List<StructurePiece> listPieces) {
//        int finishL0;// сюда записывается конечная координата коридора первого уровня
//        {
//            genCorridorWest(tm, bPos, listPieces);//Генерация первого коридора на юг рандомной длины
//            BlockPos finishPos = genCorridorWest(tm, bPos, listPieces);//получение конечной координаты сгенерированного коридора
//            genQuadNode(tm, finishPos, listPieces, Direction.EAST); //Генерация на конечной координате четырехвходной ноды
//        }
//    }
//    public static void genEastWing(TemplateManager tm, BlockPos bPos, List<StructurePiece> listPieces) {
//        int finishL0;// сюда записывается конечная координата коридора первого уровня
//        {
//            genCorridorEast(tm, bPos, listPieces);//Генерация первого коридора на юг рандомной длины
//            finishL0 = genCorridorEast(tm, bPos, listPieces);//получение конечной координаты сгенерированного коридора
//            genQuadNode(tm, bPos, listPieces, Direction.WEST, finishL0); //Генерация на конечной координате четырехвходной ноды
//        }
//    }
    public static void genQuadNode(TemplateManager tm, BlockPos p, List<StructurePiece> l, Direction d, BlockPos finishValue) {
        Rotation rotation = Rotation.NONE;
        Mirror mirror = Mirror.NONE;
        int x = 0, z = 0;

        switch (d) {
            case SOUTH:
            case NORTH:
                z = finishValue.getZ();
                break;
            case WEST:
            case EAST:
                x = finishValue.getX();
                break;
        }
        l.add(new UndergroundLabsPieces.Piece(tm, quadFragment, p, rotation, mirror, x, z));
    }
//
//            int randomNode = tr.nextInt(0, 3);
//            if (randomNode == 0) {
//                genQuadNode(tm, bPos, listPieces, Direction.SOUTH, finishL0);
//                UndergroundLabsPieces.Piece lf = (UndergroundLabsPieces.Piece) listPieces.get(listPieces.size() - 1);
//                {
//                    genCorridor(tm, lf.getTemplatePosition(), listPieces, Direction.Axis.X, 1, 0, -3, -4);
//                    genCorridor(tm, lf.getTemplatePosition(), listPieces, Direction.Axis.X, -1, 0, -5, -4);
//                    genCorridor(tm, lf.getTemplatePosition(), listPieces, Direction.Axis.Z, 1, 0, -4, -3);
//
//                    //получение координат коридоров второго уровня
//                    finishL11 = genCorridor(tm, lf.getTemplatePosition(), listPieces, Direction.Axis.X, 1, 0, -3, -4);
//                    finishL12 = genCorridor(tm, lf.getTemplatePosition(), listPieces, Direction.Axis.X, -1, 0, -5, -4);
//                    finishL13 = genCorridor(tm, lf.getTemplatePosition(), listPieces, Direction.Axis.Z, 1, 0, -4, -3);
//                    genQuadNode(tm, bPos, listPieces, Direction.SOUTH, finishL11);
//                    //   genQuadNode(tm, bPos, listPieces, Direction.SOUTH, finishL12);
//                    //  genQuadNode(tm, bPos, listPieces, Direction.SOUTH, finishL13);
//                }
//            } else if (randomNode == 1) {
//                genTripleNode(tm, bPos, listPieces, Direction.SOUTH, finishL0, Rotation.CLOCKWISE_180, 0, 0);
//                UndergroundLabsPieces.Piece lf = (UndergroundLabsPieces.Piece) listPieces.get(listPieces.size() - 1);
//                {
//                    genCorridor(tm, lf.getTemplatePosition(), listPieces, Direction.Axis.X, 1, 0, -3, -4);
//                    genCorridor(tm, lf.getTemplatePosition(), listPieces, Direction.Axis.X, -1, 0, -5, -4);
//                }
//            } else if (randomNode == 2) {
//                Rotation corRot = tr.nextBoolean() ? Rotation.CLOCKWISE_90 : Rotation.CLOCKWISE_180;
//                genCornerNode(tm, bPos, listPieces, Direction.SOUTH, finishL0, corRot, -4, 0);
//                UndergroundLabsPieces.Piece lf = (UndergroundLabsPieces.Piece) listPieces.get(listPieces.size() - 1);
//                {
//                    if (lf.getRotate() == Rotation.CLOCKWISE_90) {
//                        genCorridor(tm, lf.getTemplatePosition(), listPieces, Direction.Axis.X, -1, 0, -4, 0);
//                    }
//                    if (lf.getRotate() == Rotation.CLOCKWISE_180) {
//                        genCorridor(tm, lf.getTemplatePosition(), listPieces, Direction.Axis.X, 1, 0, -3, -4);
//                    }
//                }
//            }
//        }
//    }

//        //gen north wing labs
//        {
//
//            genCorridor(tm, bPos, listPieces, Direction.Axis.Z, -1,0,0,0);
//            int finishV = genCorridor(tm, bPos, listPieces, Direction.Axis.Z, -1,0,0,0);
//            int randomNode = tr.nextInt(0,3);
//            if(randomNode == 0) {
//                genQuadNode(tm, bPos, listPieces, Direction.NORTH, finishV);
//                UndergroundLabsPieces.Piece lf = (UndergroundLabsPieces.Piece) listPieces.get(listPieces.size() - 1);
//                {
//                   genCorridor(tm, lf.getTemplatePosition(), listPieces, Direction.Axis.X, 1, 0, 0, 0);
//                    genCorridor(tm, lf.getTemplatePosition(), listPieces, Direction.Axis.X, -1, 0, 0, 0);
//                    genCorridor(tm, lf.getTemplatePosition(), listPieces, Direction.Axis.Z, -1, 0, 0, 0);
//
//                }
//            }
//            else if(randomNode == 1) {
//                genTripleNode(tm, bPos, listPieces, Direction.NORTH, finishV, Rotation.NONE,0,-4);
//                UndergroundLabsPieces.Piece lf = (UndergroundLabsPieces.Piece) listPieces.get(listPieces.size() - 1);
//                {
//                    genCorridor(tm, lf.getTemplatePosition(), listPieces, Direction.Axis.X, 1,0,0,0);
//                    genCorridor(tm, lf.getTemplatePosition(), listPieces, Direction.Axis.X, -1,0,0,0);
//                }
//            }
//            else if(randomNode == 2) {
//                Rotation corRot = tr.nextBoolean() ? Rotation.CLOCKWISE_90 : Rotation.CLOCKWISE_180;
//                genCornerNode(tm, bPos, listPieces, Direction.NORTH, finishV, corRot,0,0);
//                UndergroundLabsPieces.Piece lf = (UndergroundLabsPieces.Piece) listPieces.get(listPieces.size() - 1);
//                {
//                    if (lf.getRotate() == Rotation.CLOCKWISE_90) {
//                       genCorridor(tm, lf.getTemplatePosition(), listPieces, Direction.Axis.X, 1, 0, -3, 0);
//                    }
//                    if (lf.getRotate() == Rotation.CLOCKWISE_180) {
//                        genCorridor(tm, lf.getTemplatePosition(), listPieces, Direction.Axis.X, -1, 0, -4, -4);
//                    }
//                }
//            }
//        }
//
//        //gen west wing labs
//        {
//            genCorridor(tm, bPos, listPieces, Direction.Axis.X, -1,0,0,0);
//            int finishV = genCorridor(tm, bPos, listPieces, Direction.Axis.X, -1,0,0,0);
//            int randomNode = tr.nextInt(0,3);
//            if(randomNode == 0) {
//                genQuadNode(tm, bPos, listPieces, Direction.WEST, finishV);
//                UndergroundLabsPieces.Piece lf = (UndergroundLabsPieces.Piece) listPieces.get(listPieces.size() - 1);
//                {
//                  genCorridor(tm, lf.getTemplatePosition(), listPieces, Direction.Axis.Z, 1, 0, 0, 0);
//                   genCorridor(tm, lf.getTemplatePosition(), listPieces, Direction.Axis.Z, -1, 0, 0, 0);
//                   genCorridor(tm, lf.getTemplatePosition(), listPieces, Direction.Axis.X, -1, 0, 0, 0);
//
//                }
//            }
//               else if(randomNode == 1) {
//            genTripleNode(tm, bPos, listPieces, Direction.WEST, finishV, Rotation.CLOCKWISE_90,0,-4);
//            UndergroundLabsPieces.Piece lf = (UndergroundLabsPieces.Piece) listPieces.get(listPieces.size() - 1);
//            {
//                genCorridor(tm, lf.getTemplatePosition(), listPieces, Direction.Axis.Z, 1,0,-4,1);
//                genCorridor(tm, lf.getTemplatePosition(), listPieces, Direction.Axis.Z, -1,0,-4,-1);
//            }
//        }
//               else if(randomNode == 2) {
//            Rotation corRot = tr.nextBoolean() ? Rotation.COUNTERCLOCKWISE_90 : Rotation.CLOCKWISE_180;
//            genCornerNode(tm, bPos, listPieces, Direction.WEST, finishV, corRot,-5,0);
//            UndergroundLabsPieces.Piece lf = (UndergroundLabsPieces.Piece) listPieces.get(listPieces.size() - 1);
//            {
//                if (lf.getRotate() == Rotation.COUNTERCLOCKWISE_90) {
//                    genCorridor(tm, lf.getTemplatePosition(), listPieces, Direction.Axis.Z, 1,0,0,-3);
//                }
//                if (lf.getRotate() == Rotation.CLOCKWISE_180) {
//                    genCorridor(tm, lf.getTemplatePosition(), listPieces, Direction.Axis.Z, -1, 0, 0, -4);
//                }
//            }
//        }
//        }
//        genCorridor(tm, bPos, listPieces, Direction.Axis.X, 1,0,0,0);
//        //сделать крылья лабораторий во все стороны / добавить фрагменты с выходом на лестницы и к лифтам / построить фрагменты лестниц, вентшахт, лифтовых шахт
//    }






//    public static int genCorridor(TemplateManager tm, BlockPos p, List<StructurePiece> l, Direction.Axis ax, int dirCorrection, int xCorrection, int zCorrection) {
//        ThreadLocalRandom tr = ThreadLocalRandom.current();
//        int randLin = tr.nextInt(6, 14);
//        ResourceLocation[] linear = { linearFragment1, linearFragment2, linearFragment3, linearFragment4 };
//        int correction = (ax == Direction.Axis.Z) ? zCorrection : xCorrection;
//        Rotation rotation = (ax == Direction.Axis.X) ? Rotation.CLOCKWISE_90 : Rotation.NONE;
//
//        // Начальный вызов рекурсивного метода
//        return generateCorridorRecursive(tm, p, l, ax, dirCorrection, xCorrection, zCorrection, linear, rotation, correction, 4, randLin * lenthFragment);
//    }
//
//    private static int generateCorridorRecursive(TemplateManager tm, BlockPos p, List<StructurePiece> l, Direction.Axis ax, int dirCorrection, int xCorrection, int zCorrection, ResourceLocation[] linear, Rotation rotation, int correction, int currentPos, int maxPos) {
//        if (currentPos >= maxPos) {
//            // Возвращаем последнюю позицию, если достигнута максимальная позиция
//            return correction + (currentPos - lenthFragment) * dirCorrection;
//        }
//
//        int pos = correction + currentPos * dirCorrection;
//        l.add(new UndergroundLabsPieces.Piece(tm, linear[ThreadLocalRandom.current().nextInt(linear.length)], p, rotation, Mirror.NONE, (ax == Direction.Axis.X) ? pos : xCorrection, (ax == Direction.Axis.Z) ? pos : zCorrection));
//
//        // Проверяем, не является ли текущая позиция последней
//        if (currentPos + lenthFragment >= maxPos) {
//            // Если да, возвращаем текущую позицию и завершаем рекурсию
//            return pos;
//        }
//
//        // Рекурсивный вызов для следующего фрагмента
//        return generateCorridorRecursive(tm, p, l, ax, dirCorrection, xCorrection, zCorrection, linear, rotation, correction, currentPos + lenthFragment, maxPos);
//    }
//    public static int genCorridor(TemplateManager tm, BlockPos p, List<StructurePiece> l, Direction.Axis ax, int dirCorrection, int xCorrection, int zCorrection) {
//        ThreadLocalRandom tr = ThreadLocalRandom.current();
//        int randLin = tr.nextInt(6, 14);
//        ResourceLocation[] linear = { linearFragment1, linearFragment2, linearFragment3, linearFragment4 };
//        int finishPos = 0;
//        int correction = (ax == Direction.Axis.Z) ? zCorrection : xCorrection;
//        Rotation rotation = (ax == Direction.Axis.X) ? Rotation.CLOCKWISE_90 : Rotation.NONE;
//        int pos;
//        for (int i = 4; i < randLin * lenthFragment; i += lenthFragment) {
//            pos = correction + i * dirCorrection;
//            l.add(new UndergroundLabsPieces.Piece(tm, linear[tr.nextInt(linear.length)], p, rotation, Mirror.NONE, (ax == Direction.Axis.X) ? pos : xCorrection, (ax == Direction.Axis.Z) ? pos : zCorrection));
//
//            finishPos = pos;
//            break;
//        }
//        return finishPos;
//    }
    public static BlockPos genCorridorSouth(TemplateManager tm, BlockPos bPos, List<StructurePiece> listPieces) {
        return genCorridor(tm, bPos, listPieces, Direction.Axis.Z, 1, 0,  0);
    }
    public static BlockPos genCorridorNorth(TemplateManager tm, BlockPos bPos, List<StructurePiece> listPieces) {
        return genCorridor(tm, bPos, listPieces, Direction.Axis.Z, -1, 0,  0);
    }
    public static BlockPos genCorridorWest(TemplateManager tm, BlockPos bPos, List<StructurePiece> listPieces) {
        return genCorridor(tm, bPos, listPieces, Direction.Axis.X, -1,4,0);
    }
    public static BlockPos genCorridorEast(TemplateManager tm, BlockPos bPos, List<StructurePiece> listPieces) {
        return genCorridor(tm, bPos, listPieces, Direction.Axis.X, 1,4,0);
    }
    public static void genInitNode(TemplateManager tm, BlockPos p, List<StructurePiece> l){
        l.add(new UndergroundLabsPieces.Piece(tm, quadFragment, p, Rotation.NONE, Mirror.NONE, 0, 0));
    }

//    public static void genTripleNode(TemplateManager tm, BlockPos p, List<StructurePiece> l, Direction d, int finishValue, Rotation rot, int zCorrection, int xCorrection) {
//        if(d == Direction.SOUTH) {
//            l.add(new UndergroundLabsPieces.Piece(tm, tripleFragment, p, rot, Mirror.NONE, 4 + xCorrection, 0, 9 + finishValue + zCorrection));
//        }
//        if(d == Direction.NORTH) {
//            l.add(new UndergroundLabsPieces.Piece(tm, tripleFragment, p, rot, Mirror.NONE, 4 + xCorrection, 0, 9 + finishValue + zCorrection));
//        }
//        if(d == Direction.WEST) {
//            l.add(new UndergroundLabsPieces.Piece(tm, tripleFragmentMirrored, p, rot, Mirror.NONE, 4 + xCorrection + finishValue, 0,  zCorrection));
//        }
//    }
    public static void genCornerNode(TemplateManager tm, BlockPos p, List<StructurePiece> l, Direction d, int finishValue, Rotation rot, int zCorrection, int xCorrection) {
        if(d == Direction.SOUTH) {
            l.add(new UndergroundLabsPieces.Piece(tm, cornerFragment, p, rot, Mirror.NONE, 4 + xCorrection, 9 + finishValue + zCorrection));
        }
        if(d == Direction.NORTH) {
            l.add(new UndergroundLabsPieces.Piece(tm, cornerFragmentMirrored, p, rot, Mirror.NONE, 4 + xCorrection, 9 + finishValue + zCorrection));
        }
        if(d == Direction.WEST) {
            l.add(new UndergroundLabsPieces.Piece(tm, cornerFragment, p, rot, Mirror.NONE, 4 + finishValue + xCorrection, 9 + zCorrection));
        }
    }



    public static class Piece extends TemplateStructurePiece {
        private final ResourceLocation location;
        private final Rotation rot;
        private final Mirror mir;

        private int xCorrection,zCorrection;

        public Piece(TemplateManager tm, ResourceLocation rs, BlockPos bp, Rotation rot, Mirror mir, int xCorrection, int zCorrection) {
            super(UG_LABS_PIECES, 0);
            putValueInMap();
            this.location = rs;
            this.rot = rot;
            this.mir = mir;
            this.templatePosition = bp.add(xCorrection, 0, zCorrection);
            this.zCorrection = zCorrection;
            this.xCorrection = xCorrection;
            this.tempMngSetup(tm);
        }


        public Piece(TemplateManager tm, CompoundNBT nbt) {
            super(UG_LABS_PIECES, nbt);
            this.location = new ResourceLocation(nbt.getString("Template"));
            this.rot = Rotation.NONE;
            this.mir = Mirror.NONE;
            this.tempMngSetup(tm);
        }
        public Mirror getMirror() {
            return mir;
        }
        public Rotation getRotate() {
            return rot;
        }
        public BlockPos getPosition(ResourceLocation rs) {
            return POOL.get(rs);
        }
        public ResourceLocation getLocation() {
            return location;
        }
        public BlockPos getTemplatePosition() {
            return templatePosition;
        }
        private void tempMngSetup(TemplateManager tm) {
            Template template = tm.getTemplateDefaulted(this.location);
            PlacementSettings placementsettings = (
                    new PlacementSettings())
                    .setRotation(this.rot)
                    .setMirror(this.mir);
            this.setup(template, this.templatePosition, placementsettings);
        }


        protected void handleDataMarker(String function, BlockPos pos, IServerWorld worldIn, Random rand, MutableBoundingBox sbb) {

        }

        //генератор заработал, настраивать, компоновать лабы.
        public boolean func_230383_a_(ISeedReader sr, StructureManager sm, ChunkGenerator cg, Random r, MutableBoundingBox mbb, ChunkPos cp, BlockPos bp) {
            PlacementSettings ps = (
                    new PlacementSettings())
                    .setRotation(Rotation.NONE)
                    .setMirror(Mirror.NONE)
                    .setCenterOffset(UndergroundLabsPieces.POOL.get(this.location))
                    .addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);

            BlockPos blockpos = UndergroundLabsPieces.POOL.get(this.location);
            if(blockpos == null) return false;
            BlockPos blockpos1 = this.templatePosition.add(
            Template.transformedBlockPos(ps, new BlockPos(blockpos.getX(), 0, blockpos.getZ())));
            int i = sr.getHeight(Heightmap.Type.WORLD_SURFACE_WG, blockpos1.getX(), blockpos1.getZ());
            BlockPos blockpos2;

            blockpos2 = this.templatePosition;

            boolean flag = false;

            if(this.location == stairsMid) {

                for (int y2 = 0; y2 < i - 22; y2 += 4) {
                    this.templatePosition = new BlockPos(this.templatePosition.getX(), 21, this.templatePosition.getZ());
                    this.templatePosition = this.templatePosition.add(0, y2, 0);
                    flag = super.func_230383_a_(sr, sm, cg, r, mbb, cp, bp);

                }
            } else if(this.location == stairsTop) {
                this.templatePosition = this.templatePosition.add(0, i-92, 0);
                flag = super.func_230383_a_(sr, sm, cg, r, mbb, cp, bp);
            } else {
                this.templatePosition = this.templatePosition.add(0, -74, 0);
                flag = super.func_230383_a_(sr, sm, cg, r, mbb, cp, bp);
            }
            this.templatePosition = blockpos2;
            return flag;
        }
    }
}
