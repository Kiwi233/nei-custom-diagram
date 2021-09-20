package com.github.dcysteine.neicustomdiagram.generators.gregtech.materialtools;

import com.github.dcysteine.neicustomdiagram.api.diagram.DiagramGroupInfo;
import com.github.dcysteine.neicustomdiagram.api.diagram.interactable.AllDiagramsButton;
import com.github.dcysteine.neicustomdiagram.api.diagram.layout.Grid;
import com.github.dcysteine.neicustomdiagram.api.diagram.layout.Layout;
import com.github.dcysteine.neicustomdiagram.api.diagram.layout.Lines;
import com.github.dcysteine.neicustomdiagram.api.diagram.layout.Slot;
import com.github.dcysteine.neicustomdiagram.api.diagram.layout.SlotGroup;
import com.github.dcysteine.neicustomdiagram.api.diagram.tooltip.Tooltip;
import com.github.dcysteine.neicustomdiagram.api.draw.Point;
import com.github.dcysteine.neicustomdiagram.mod.Lang;
import com.google.common.collect.ImmutableList;

class LayoutHandler {
    static final Point MATERIAL_INFO_POSITION = Grid.GRID.grid(2, 0);

    static final class SlotKeys {
        static final String TURBINE_BLADE = "turbine-blade";
        static final String ARROWHEAD = "arrowhead";
    }

    static final class SlotGroupKeys {
        static final String TOOLS = "tools";
        static final String TOOL_PARTS = "tool-parts";

        static final String TURBINES = "turbines";
        static final String ARROWS = "arrows";

        static final String SCANNERS = "scanners";
        static final String ELECTRIC_SCANNERS = "electric-scanners";
    }

    private final DiagramGroupInfo info;

    private ImmutableList<Layout> requiredLayouts;
    private ImmutableList<Layout> optionalLayouts;

    LayoutHandler(DiagramGroupInfo info) {
        this.info = info;
        this.requiredLayouts = null;
        this.optionalLayouts = null;
    }

    /** This method must be called before any other methods are called. */
    void initialize() {
        ImmutableList.Builder<Layout> requiredLayoutsBuilder = new ImmutableList.Builder<>();
        requiredLayoutsBuilder.add(buildHeaderLayout());
        requiredLayouts = requiredLayoutsBuilder.build();

        ImmutableList.Builder<Layout> optionalLayoutsBuilder = new ImmutableList.Builder<>();
        optionalLayoutsBuilder.add(buildToolPartsLayout());
        optionalLayoutsBuilder.add(buildToolsLayout());
        optionalLayoutsBuilder.add(buildTurbineBladeLayout());
        optionalLayoutsBuilder.add(buildTurbinesLayout());
        optionalLayoutsBuilder.add(buildArrowheadLayout());
        optionalLayoutsBuilder.add(buildArrowsLayout());
        optionalLayoutsBuilder.add(buildScannersLayout());
        optionalLayouts = optionalLayoutsBuilder.build();
    }

    ImmutableList<Layout> requiredLayouts() {
        return requiredLayouts;
    }

    ImmutableList<Layout> optionalLayouts() {
        return optionalLayouts;
    }

    private Layout buildHeaderLayout() {
        return Layout.builder()
                .addInteractable(new AllDiagramsButton(info, Grid.GRID.grid(0, 0)))
                .build();
    }

    private Layout buildToolPartsLayout() {
        return Layout.builder()
                .putSlotGroup(
                        SlotGroupKeys.TOOL_PARTS,
                        SlotGroup.builder(4, 4, Grid.GRID.grid(6, 6), Grid.Direction.NE)
                                .setDefaultTooltip(
                                        Tooltip.create(
                                                Lang.GREGTECH_MATERIAL_TOOLS.trans("toolpartsslot"),
                                                Tooltip.SLOT_FORMATTING))
                                .build())
                .build();
    }

    private Layout buildToolsLayout() {
        return Layout.builder()
                .addLines(
                        Lines.builder(Grid.GRID.grid(6, 6))
                                .addArrow(Grid.GRID.edge(6, 8, Grid.Direction.N))
                                .build())
                .putSlotGroup(
                        SlotGroupKeys.TOOLS,
                        SlotGroup.builder(9, 5, Grid.GRID.grid(6, 8), Grid.Direction.S)
                                .setDefaultTooltip(
                                        Tooltip.create(
                                                Lang.GREGTECH_MATERIAL_TOOLS.trans("toolsslot"),
                                                Tooltip.SLOT_FORMATTING))
                                .build())
                .build();
    }

    private Layout buildTurbineBladeLayout() {
        return Layout.builder()
                .putSlot(
                        SlotKeys.TURBINE_BLADE,
                        Slot.builder(Grid.GRID.grid(0, 2))
                                .setTooltip(
                                        Tooltip.create(
                                                Lang.GREGTECH_MATERIAL_TOOLS.trans(
                                                        "turbinebladeslot"),
                                                Tooltip.SLOT_FORMATTING))
                                .build())
                .build();
    }

    private Layout buildTurbinesLayout() {
        return Layout.builder()
                .addLines(
                        Lines.builder(Grid.GRID.grid(0, 2))
                                .addArrow(Grid.GRID.edge(2, 2, Grid.Direction.W))
                                .build())
                .putSlotGroup(
                        SlotGroupKeys.TURBINES,
                        SlotGroup.builder(2, 2, Grid.GRID.grid(2, 2), Grid.Direction.SE)
                                .setDefaultTooltip(
                                        Tooltip.create(
                                                Lang.GREGTECH_MATERIAL_TOOLS.trans("turbinesslot"),
                                                Tooltip.SLOT_FORMATTING))
                                .build())
                .build();
    }

    private Layout buildArrowheadLayout() {
        return Layout.builder()
                .putSlot(
                        SlotKeys.ARROWHEAD,
                        Slot.builder(Grid.GRID.grid(0, 6))
                                .setTooltip(
                                        Tooltip.create(
                                                Lang.GREGTECH_MATERIAL_TOOLS.trans("arrowheadslot"),
                                                Tooltip.SLOT_FORMATTING))
                                .build())
                .build();
    }

    private Layout buildArrowsLayout() {
        return Layout.builder()
                .addLines(
                        Lines.builder(Grid.GRID.grid(0, 6))
                                .addArrow(Grid.GRID.edge(2, 6, Grid.Direction.W))
                                .build())
                .putSlotGroup(
                        SlotGroupKeys.ARROWS,
                        SlotGroup.builder(2, 1, Grid.GRID.grid(2, 6), Grid.Direction.E)
                                .setDefaultTooltip(
                                        Tooltip.create(
                                                Lang.GREGTECH_MATERIAL_TOOLS.trans("arrowsslot"),
                                                Tooltip.SLOT_FORMATTING))
                                .build())
                .build();
    }

    private Layout buildScannersLayout() {
        return Layout.builder()
                .addLines(
                        Lines.builder(Grid.GRID.grid(4, 17))
                                .addSegment(Grid.GRID.grid(10, 17))
                                .build())
                .putSlotGroup(
                        SlotGroupKeys.SCANNERS,
                        SlotGroup.builder(5, 2, Grid.GRID.grid(4, 17), Grid.Direction.C)
                                .setDefaultTooltip(
                                        Tooltip.create(
                                                Lang.GREGTECH_MATERIAL_TOOLS.trans("scannersslot"),
                                                Tooltip.SLOT_FORMATTING))
                                .build())
                .putSlotGroup(
                        SlotGroupKeys.ELECTRIC_SCANNERS,
                        SlotGroup.builder(2, 2, Grid.GRID.grid(10, 17), Grid.Direction.C)
                                .setDefaultTooltip(
                                        Tooltip.create(
                                                Lang.GREGTECH_MATERIAL_TOOLS.trans(
                                                        "electricscannersslot"),
                                                Tooltip.SLOT_FORMATTING))
                                .build())
                .build();
    }
}
