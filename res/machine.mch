MACHINE name

    CONST_DEFS
        c0 = false
        c1 = true
        c2 = true || false && c0 && c1 || c2
        c3 = 0
        c4 = 1 + c3
        c5 = 42.0 + ---c4
        c6 = {}
        c7 = {{}, {c0, c1, c2}, {c3 > 0}, c6}
        c8 = {{{c3, c4, c5}, {c4, c5}, {c5}}, {c0, c1, c2}, {c4 > 0}, c6, c7}
        c9 = c8

    SET_DEFS
        s0 = {}
        s1 = {s0, c0, c1, c3}
        s2 = {s0, s1, c7, c8, c9}
        s3 = s2

    VAR_DEFS
        v0 : s0
        v1 : s1
        v2 : {s1, s2, s3}
        v3 : {s1, s2, s3, c0, c1}
        v4 : {c3 + 1, c4 + 2, c5 + 3}
