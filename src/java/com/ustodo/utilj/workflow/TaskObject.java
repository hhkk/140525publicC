package com.ustodo.utilj.workflow;

public interface TaskObject
{
   public enum State { READY, WAITING, DONE };
   // READY = ready to execute next step
   // WAITING = awaiting some external condition
   // DONE = finished all steps

   public int getCurrentStep();
   // returns # of current step

   public int getEndStep();
   // returns # of step which is the DONE case.

   public State getState();
   // checks state and returns it. 
   // multiple calls will always be identical, 
   // except WAITING which can transition to READY or DONE.

   public State executeStep();
   // if READY, executes next step and returns getState().
   // otherwise, returns getState().

}

