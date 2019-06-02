MACHINE elec

	CONST_DEFS
		int n = 3

	SET_DEFS
		set<int> BATDOM = [1..n]
		set<string> BATCODOM = {"ko", "ok"}

	VAR_DEFS
		string h : {"tic", "tac"}
		int sw : [1..n]

	FUN_DEFS
		int -> string bat : BATDOM -> BATCODOM

	INVARIANT
		true

	INITIALISATION
		h := "tic";
		sw := 1;
		bat(1) := "ok";
		bat(2) := "ok";
		bat(3) := "ok"

	EVENTS

		Tic =
			SELECT
				h = "tac"
			THEN
				h := "tic"
			END
