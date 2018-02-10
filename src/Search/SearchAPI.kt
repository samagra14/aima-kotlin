package Search

@FunctionalInterface
interface InitialStateFunction<S> {
    /**
     * @return the initial state that the agent starts in.
     */
    fun initialState(): S
}


@FunctionalInterface
interface ActionsFunction<out A, in S> {
    /**
     * A description of the possible actions available to the agent from a give
     * state.
     *
     * @param s
     * a given state s
     * @return the set of actions that can be executed in s (returned in a list
     * for access convenience, implementation must guarantee Set
     * semantics).
     */
    fun actions(state: S): List<A>
}

@FunctionalInterface
interface TransitionModel< in A, S>{
    fun result(state:S,action:A):S
}

@FunctionalInterface
interface GoalTest<S>{
    fun isGoalState(goalList:Set<S>,state:S):Boolean
}

@FunctionalInterface
interface StepCostFunction<A, S> {
    /**
     * Calculates the step-cost between two states. Used to assign a numeric
     * cost to each path.
     *
     */
    fun stepCost(s: S, a: A, sPrime: S): Double
}


interface Problem<A, S> :
// S initialState()
        InitialStateFunction<S>,
        // List<A> actions(S s)
        ActionsFunction<A, S>,
        // S result(S s, A a)
        TransitionModel<A, S>,
        // boolean isGoalState(S s)
        GoalTest<S>,
        // double stepCost(S s, A a, S s')
        StepCostFunction<A, S>