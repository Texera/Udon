from threading import Event, Condition
from typing import Optional, Union, Tuple, Iterator, List, MutableMapping

from core.models import InputExhausted
from proto.edu.uci.ics.amber.engine.common import LinkIdentity


class TupleProcessingManager:
    def __init__(self):
        self.current_input_tuple: Optional[Union[Tuple, InputExhausted]] = None
        self.current_input_link: Optional[LinkIdentity] = None
        self.current_input_tuple_iter: Optional[
            Iterator[Union[Tuple, InputExhausted]]
        ] = None
        self.current_output_tuple: Optional[Tuple] = None
        self.input_links: List[LinkIdentity] = list()
        self.input_link_map: MutableMapping[LinkIdentity, int] = dict()
        self._context_switch_condition: Condition = Condition()
        self._context_switch_condition_with_no_pdb: Condition = Condition()
        self.finished_current: Event = Event()
        self.with_pdb = True

    def get_output_tuple(self) -> Optional[Tuple]:
        ret, self.current_output_tuple = self.current_output_tuple, None
        return ret

    @property
    def context_switch_condition(self):
        if self.with_pdb:
            return self._context_switch_condition
        else:
            return self._context_switch_condition_with_no_pdb

