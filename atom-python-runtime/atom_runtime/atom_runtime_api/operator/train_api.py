#############################################################################
#Copyright (c) [Year] [name of copyright holder]
#[Software Name] is licensed under Mulan PubL v2.
#You can use this software according to the terms and conditions of the Mulan PubL v2.
#You may obtain a copy of Mulan PubL v2 at:
#         http://license.coscl.org.cn/MulanPubL-2.0
#THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND,
#EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT,
#MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE.
#See the Mulan PubL v2 for more details.
#############################################################################
from atom_runtime.atom_runtime_api.operator.operators_api import OperatorApi

class TrainOperatorApi(OperatorApi):
    
    labels:object

    def __init__(self) -> None:
        super().__init__()

    def initialization(self):
        super().initialization(self)

    def execute(self, data):
        pass

    def result(self):
       pass
    
    def comparision_execute(self, data):
        pass

    