export interface Task {
  id?: number,
  title: string,
  description: string,
  status?: string
}

// models/page.model.ts
export interface Page<T> {
  content: T[];
  pageable: {
    pageNumber: number;
    pageSize: number;
    sort: any[];
    offset: number;
    paged: boolean;
    unpaged: boolean;
  };
  last: boolean;
  totalElements: number;
  totalPages: number;
  size: number;
  number: number;
  sort: any[];
  first: boolean;
  numberOfElements: number;
  empty: boolean;
}

