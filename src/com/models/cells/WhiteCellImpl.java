package com.models.cells;

import com.globals.Defaults;

class WhiteCellImpl extends CellImpl {
    public WhiteCellImpl() {
        super(Defaults.colorScheme.getLight());
    }
}
