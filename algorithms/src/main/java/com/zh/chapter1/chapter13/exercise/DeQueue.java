package com.zh.chapter1.chapter13.exercise;

/**
 * 双向链表实现的队列
 * 没有使用虚拟头尾节点
 * @author zh
 * 2018年8月16日
 */
public class DeQueue<T> {
    /**节点*/
    private class DoubleNode {
        private T item; // 数据
        private DoubleNode previous; // 前面的节点
        private DoubleNode next; // 后面的节点
    }
    
    private DoubleNode head;
    private DoubleNode tail;
    private Integer N;
    
    public DeQueue() {
        N = 0;
    }
    
    public void insertHead(T t) {
        DoubleNode dn = new DoubleNode();
        dn.item = t;
        if (head == null) {
            head = dn;
            tail = dn;
        } else {
            dn.next = head;
            head.previous = dn;
            head = dn;
        }
        N++;
    }
    
    public void insertTail(T t) {
        DoubleNode dn = new DoubleNode();
        dn.item = t;
        if (tail == null) {
            tail = dn;
            head = dn;
        } else {
            dn.previous = tail;
            tail.next = dn;
            tail = dn;
        }
        N++;
    }
    
    public T deleteHead() {
        if (head == null) return null;
        else {
            T t = head.item;
            if (tail == head) {
                head = tail = null;
            } else {
                head = head.next;
                head.previous = null;
            }
            N--;
            return t;
        }
    }
    
    public T deleteTail() {
        if (tail == null) return null;
        else {
            T t = tail.item;
            if (head == tail) {
                tail = head = null;
            } else {
                tail = tail.previous;
                tail.next = null;
            }
            N--;
            return t;
        }
    }
    
    public void insertBefore(T t, T newItem) {
        DoubleNode node = new DoubleNode();
        node.item = newItem;
        
        DoubleNode dn = searchNode(t);
        if (dn != null) {
            DoubleNode oldNext = dn.previous;
            
            if (oldNext != null) {
                oldNext.next = node;
                node.previous = oldNext;
                
                node.next = dn;
                dn.previous = node;
            } else {
                // 说明是首节点
                node.next = dn;
                dn.previous = node;
                head = node;
            }
            N++;
        }
    }
    
    /**
     * 在t后面插入newItem
     * @param t
     * @param newItem
     */
    public void insertAfter(T t, T newItem) {
        DoubleNode node = new DoubleNode();
        node.item = newItem;
        
        DoubleNode dn = searchNode(t);
        if (dn != null) {
            DoubleNode oldNext = dn.next;
            
            if (oldNext != null) {
                dn.next = node;
                node.previous = dn;
                
                oldNext.previous = node;
                node.next = oldNext;
            } else {
                // 说明是尾节点
                node.previous = dn;
                dn.next = node;
                tail = node;
            }
            N++;
        }
    }
    
    /**
     * 删除第一个t节点
     * @param t
     */
    public void delete(T t) {
    	DoubleNode dn = searchNode(t);
    	if (dn == null) return;
    	else {
    		DoubleNode previous = dn.previous;
    		DoubleNode next = dn.next;
    		if (previous != null) {
    			previous.next = next;
    		} else {
    			// 说明是首节点
    			head = next;
    		}
    		if (next != null) {
    			next.previous = previous;
    		} else {
    			// 说明是尾节点
    			tail = previous;
    		}
    		N--;
    	}
    }
    
    private DoubleNode searchNode(T t) {
        DoubleNode dn = head;
        while(dn != null) {
            if (t.equals(dn.item)) {
                return dn;
            }
            dn = dn.next;
        }
        return null;
    }
    
    public int size() {
    	return N;
    }
}
