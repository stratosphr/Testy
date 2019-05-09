MACHINE name

    CONST_DEFS
        c0 = false
        c1 = true
        c2 = true || false && c0 && c1 || c0
        c3 = 0
        c4 = 1 + c3
        c5 = 42.0 + ---c4
        c6 = {}
        c7 = {{}, {c0, c1, c2}, {c3 > 0}, c6}
        c8 = {{{c3, c4, c5}, {c4, c5}, {c5}}, {c0, c1, c2}, {c4 > 0}, c6, c7}
        c9 = c8
        c10 = c0 : {false, !c1, c3 !: {0, 1, 2, 3}}
        c11 = [c3 .. 1 + c4 * 2 * 4 + 3 + 4]

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

    FUN_DEFS
        f0 : {} -> {}
        f1 : {c3, c4, c5} -> {}
        f2 : {} -> {c3, c4, c5}
        f3 : {c3, c4, c5} -> {c3, c4, c5}
        f4 : s0 -> s1
        f5 : s2 -> c7
        f6 : c8 -> s2

    INVARIANT
        !(v2 != v3)

    INITIALISATION
        SELECT
            v1 = v3 || v4 = c3
        THEN
            IF
                v2 != {} && v4 : {c3 + 1, c4 + 2}
            THEN
                SELECT
                    v1 = v3 || v4 != c4
                THEN
                    v4 := c4 + 2;
                    v1 := 0;
                    v2 := c0 => c1
                END
            ELSE
                CHOICE
                    v4 := c5 + 3
                OR
                    v4 := c4 + 2;
                    v1 := 0;
                OR
                    SKIP
                OR
                    SELECT
                        v1 != v3
                    THEN
                        v4 := c4 + 2;
                        v1 := 0;
                        v2 := c0 => c1
                    END
                OR
                    ANY
                        x : c11,
                        y : {true, false},
                        f : {v0, v1} -> {v0, v1, v2, 1 + 3 + 4 - 5 + 6}
                    WHERE
                        f3(x) = x
                    THEN
                        SELECT
                            v1 != v3
                        THEN
                            v1 := v3
                        END
                    END
                END
            END
        END

    EVENTS

        e0 =
            ANY
                x : c11,
                y : {true, false},
                f : {v0, v1} -> {v0, v1, v2, 1 + 3 + 4 - 5 + 6}
            WHERE
                f3(x) = x
            THEN
                SELECT
                    v1 != v3
                THEN
                    v1 := v3
                END
            END

        e1 =
            SKIP

        e2 =
            SELECT
                (c0 || €(x : {v4, c5}, y : {true, false}).(x = v4)) &&
                (c1 || #(x : {v4, 10 + 11, 9 - c5}, y : {x, 0}).((x != v4)))
            THEN
                v4 := c4 + 2;
                v1 := 0;
                v2 := c0 => c1
            END
